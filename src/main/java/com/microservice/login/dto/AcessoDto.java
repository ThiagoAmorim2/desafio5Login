package com.microservice.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class AcessoDto {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty(value = "usuario")
    @NotBlank(message = "O campo usuário é de preenchimento obrigatório")
    @Length(min = 5, max = 10, message = "O tamanho mínimo do campo é {min} e o máximo é {max}")
    private String usuario;

    @JsonProperty(value = "senha")
    @NotBlank(message = "O campo senha é de preenchimento obrigatório")
    @Length(min = 5, max = 8, message = "O tamanho mínimo do campo é {min} e o máximo é {max}")
    private String senha;

    @JsonProperty(value = "funcao")
    @NotBlank(message = "O campo função é de preenchimento obrigatório")
    private String funcao;

    public AcessoDto(UUID id, String usuario, String senha, String funcao) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.funcao = funcao;
    }

    public AcessoDto() {
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

    public UUID getId() {
        return id;
    }
}
