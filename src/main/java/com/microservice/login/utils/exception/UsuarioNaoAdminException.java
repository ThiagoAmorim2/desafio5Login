package com.microservice.login.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;


public class UsuarioNaoAdminException extends RuntimeException {
    public UsuarioNaoAdminException(String message) {
        super(message);
    }
}
