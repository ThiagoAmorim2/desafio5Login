package com.microservice.login.utils.exception;

public class UsuarioNaoAdminException extends RuntimeException {
    public UsuarioNaoAdminException(String message) {
        super(message);
    }
}
