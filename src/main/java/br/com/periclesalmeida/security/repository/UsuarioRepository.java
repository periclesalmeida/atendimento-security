package br.com.periclesalmeida.security.repository;

import br.com.periclesalmeida.security.domain.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "atendimento")
public interface UsuarioRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/usuario/login/{login}")
    Usuario findByLogin(@PathVariable("login") String login);
}
