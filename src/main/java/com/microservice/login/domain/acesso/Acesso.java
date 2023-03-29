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
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(unique = true)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O campo usuário é de preenchimento obrigatório")
    @Length(min = 3, max = 10, message = "O tamanho mínimo do campo é {min} e o máximo é {max}")
    private String usuario;

    @Column(nullable = false)
    @NotBlank(message = "O campo senha é de preenchimento obrigatório")
    @Length(min = 5, max = 8, message = "O tamanho mínimo do campo é {min} e o máximo é {max}")
    private String senha;

    @Column(nullable = false)
    @NotBlank(message = "O campo função é de preenchimento obrigatório")
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
