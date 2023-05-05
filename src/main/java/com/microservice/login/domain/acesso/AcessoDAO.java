package com.microservice.login.domain.acesso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@NamedNativeQueries({
    @NamedNativeQuery(
        name = "Acesso.buscarTodosAcessos",
        query = "SELECT * FROM tb_login WHERE usuario LIKE :usuario"
    ),
    @NamedNativeQuery(
        name = "Acesso.contagemTodosAcessos",
        query = "SELECT COUNT(*) FROM tb_login WHERE usuario LIKE :usuario"
    )
})

@Entity
@Table(name = "tb_login")
public class AcessoDAO implements Serializable {

    private static final long seralVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

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

    public AcessoDAO(Long id, String nomeUsuario, String senha, String funcao) {
        this.id = id;
        this.usuario = nomeUsuario;
        this.senha = senha;
        this.funcao = funcao;
    }

    public AcessoDAO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
