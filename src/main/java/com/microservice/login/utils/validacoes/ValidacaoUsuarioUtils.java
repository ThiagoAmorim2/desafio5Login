package com.microservice.login.utils.validacoes;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoUsuarioUtils {
    public boolean ehUmUsuarioValido(Acesso acesso){
        if(acesso.getFuncao().equals("admin")) {
            return true;
        }
        return false;
    }
}
