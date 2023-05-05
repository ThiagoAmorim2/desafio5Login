package com.microservice.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AcessoDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "usuario")
    private String usuario;

    @JsonProperty(value = "senha")
    private String senha;

    @JsonProperty(value = "funcao")
    private String funcao;

    public AcessoDTO(String usuario, String senha, String funcao) {
        this.usuario = usuario;
        this.senha = senha;
        this.funcao = funcao;
    }

    public AcessoDTO() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
