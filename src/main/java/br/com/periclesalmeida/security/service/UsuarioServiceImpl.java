package br.com.periclesalmeida.security.service;

import br.com.periclesalmeida.security.config.security.UsuarioSecurity;
import br.com.periclesalmeida.security.domain.Permissao;
import br.com.periclesalmeida.security.domain.Usuario;
import br.com.periclesalmeida.security.repository.UsuarioRepository;
import br.com.periclesalmeida.security.util.NegocioException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    private final String MENSAGEM_USUARIO_NÃO_POSSUI_PERMISSÃO = "Usuário não possui permissão.";
    private final String MENSAGEM_USUARIO_E_OU_SENHA_INVALIDO = "Usuário e/ou senha inválido";

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuarioConsultado = usuarioRepository.findByLogin(username);
        lancarExcecaoCasoUsuarionaoExista(usuarioConsultado);
        lancarExcecaoCasoUsuarioNaoPossuaPermissao(usuarioConsultado.getPermissoes());
        return new UsuarioSecurity(usuarioConsultado, criarGrantedAuthority(usuarioConsultado));
    }

    private void lancarExcecaoCasoUsuarionaoExista(Usuario usuarioOptional) {
        Optional.ofNullable(usuarioOptional).orElseThrow(() -> new UsernameNotFoundException(MENSAGEM_USUARIO_E_OU_SENHA_INVALIDO));
    }

    private void lancarExcecaoCasoUsuarioNaoPossuaPermissao(Set<Permissao> permissoes) {
        Optional.ofNullable(permissoes).orElseThrow(() -> new NegocioException(MENSAGEM_USUARIO_NÃO_POSSUI_PERMISSÃO));
    }

    private Collection<? extends GrantedAuthority> criarGrantedAuthority(Usuario usuario) {
        return usuario.getPermissoes().stream()
                .map(permissao -> new SimpleGrantedAuthority(permissao.getId().toUpperCase()))
                .collect(Collectors.toList());
    }
}
