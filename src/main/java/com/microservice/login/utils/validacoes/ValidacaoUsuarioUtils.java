package com.microservice.login.utils.validacoes;

import org.springframework.stereotype.Component;

import com.microservice.login.domain.acesso.AcessoDAO;

@Component
public class ValidacaoUsuarioUtils {
    public boolean ehUmUsuarioValido(AcessoDAO acesso){
        if(acesso.getFuncao().equals("admin")) {
            return true;
        }
        return false;
    }
}
