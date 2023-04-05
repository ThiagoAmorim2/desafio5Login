package com.microservice.login.utils.exception;

public class UsuarioNaoExisteException extends RuntimeException{
    public UsuarioNaoExisteException(String message) {
        super(message);
    }
}
