package com.microservice.login.utils.exception;

public class AcessoNotFoundException extends RuntimeException {

    public AcessoNotFoundException(Long id) {
        super("Acesso não cadastrado para o id: " + id);
    }
}
