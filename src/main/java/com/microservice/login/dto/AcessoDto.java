package com.microservice.login.dto;

public class AcessoDto {
    private String usuario;
    private String senha;
    private String funcao;

    public AcessoDto(String usuario, String senha, String funcao) {
        this.usuario = usuario;
        this.senha = senha;
        this.funcao = funcao;
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
}
