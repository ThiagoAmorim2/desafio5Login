package com.microservice.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class AcessoDto {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty(value = "nome_usuario")
    private String nomeUsuario;

    @JsonProperty(value = "senha")
    private String senha;

    @JsonProperty(value = "funcao")
    private String funcao;

    public AcessoDto(String nomeUsuario, String senha, String funcao) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.funcao = funcao;
    }

    public AcessoDto() {
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String usuario) {
        this.nomeUsuario = nomeUsuario;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
