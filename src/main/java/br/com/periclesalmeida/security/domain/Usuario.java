package br.com.periclesalmeida.security.domain;

import java.util.Objects;
import java.util.Set;

public class Usuario {

    private String id;
    private String login;
    private String senha;
    private Boolean ativo;
    private Set<Permissao> permissoes;

    public Usuario(String login, String senha, Boolean ativo, Set<Permissao> permissoes) {
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.permissoes = permissoes;
    }

    public Usuario() {
    }

    public Usuario( String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }
    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
