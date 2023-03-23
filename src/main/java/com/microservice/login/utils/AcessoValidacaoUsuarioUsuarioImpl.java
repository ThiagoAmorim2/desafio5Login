package com.microservice.login.utils;

import com.microservice.login.domain.acesso.AcessoValidacaoUsuarioBase;
import com.microservice.login.utils.exception.ValidacaoFuncaoException;
import com.microservice.login.utils.exception.ValidacaoSenhaException;
import com.microservice.login.utils.exception.ValidacaoUsuarioException;
import org.springframework.stereotype.Service;

@Service
public class AcessoValidacaoUsuarioUsuarioImpl implements AcessoValidacaoUsuarioBase {
    @Override
    public void validaAtualizacaoUsuario(String usuario) {
        if(usuario == null){
            throw new ValidacaoUsuarioException("Precisa informar qual usuário");
        }
    }

    @Override
    public void validaAtualizacaoSenha(String senha) {
        if(senha.isBlank() || senha == null){
            throw new ValidacaoSenhaException("Usuário precisa informar a senha");
        }
    }

    @Override
    public void validaAtualizacaoFuncao(String funcao) {
        if(funcao.isBlank() || funcao == null){
            throw new ValidacaoFuncaoException("Usuário precisa informar a função");
        }
    }
}
