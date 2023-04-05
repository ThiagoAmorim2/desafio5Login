package com.microservice.login.domain.acesso;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_LOGIN")
public class Acesso implements Serializable {

    private static final long seralVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O campo usuário é de preenchimento obrigatório")
    @Length(min = 3, max = 10, message = "O tamanho mínimo do campo é {min} e o máximo é {max}")
    private String usuario;

    @Column(nullable = false)
    @NotBlank(message = "O campo senha é de preenchimento obrigatório")
    private String senha;

    @Column(nullable = false)
    @NotBlank(message = "O campo função é de preenchimento obrigatório")
    private String funcao;

    public Acesso(UUID id, String nomeUsuario, String senha, String funcao) {
        this.id = id;
        this.usuario = nomeUsuario;
        this.senha = senha;
        this.funcao = funcao;
    }

    public Acesso() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return usuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.usuario = nomeUsuario;
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
