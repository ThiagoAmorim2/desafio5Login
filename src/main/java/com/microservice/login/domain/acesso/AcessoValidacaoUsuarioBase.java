package com.microservice.login.domain.acesso;

public interface AcessoValidacaoUsuarioBase {
    void validaAtualizacaoUsuario(String usuario);

    void validaAtualizacaoSenha(String senha);

    void validaAtualizacaoFuncao(String funcao);
}
