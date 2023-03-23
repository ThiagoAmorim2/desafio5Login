package com.microservice.login.domain.acesso;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_LOGIN")
public class Acesso implements Serializable {

    private static final long seralVersionUID = 1l;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(unique = true)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false, length = 8)
    private String senha;

    @Column(nullable = false)
    private String funcao;

    public Acesso(String usuario, String senha, String funcao) {
        this.usuario = usuario;
        this.senha = senha;
        this.funcao = funcao;
    }

    public Acesso() {
    }

    public UUID getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}