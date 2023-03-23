package com.microservice.login.utils.exception;

import com.microservice.login.domain.acesso.Acesso;
import org.hibernate.PropertyValueException;

public class ValidacaoUsuarioException extends RuntimeException {
    public ValidacaoUsuarioException(String message) {
        super(message);
    }
}
