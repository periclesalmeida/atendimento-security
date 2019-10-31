package br.com.periclesalmeida.security.domain;

import java.io.Serializable;
import java.util.Objects;

public class Permissao implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String descricao;

    public Permissao(String codigo, String descricao) {
        this.id = codigo;
        this.descricao = descricao;
    }

    public Permissao() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissao permissao = (Permissao) o;
        return Objects.equals(id, permissao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
