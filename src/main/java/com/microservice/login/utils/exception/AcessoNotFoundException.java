package com.microservice.login.utils.exception;

import java.util.UUID;

public class AcessoNotFoundException extends RuntimeException {

    public AcessoNotFoundException(UUID id) {
        super("Acesso não cadastrado para o id: " + id);
    }
}
