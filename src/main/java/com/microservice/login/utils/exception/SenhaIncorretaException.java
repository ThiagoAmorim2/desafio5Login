package com.microservice.login.utils.exception;

public class SenhaIncorretaException extends RuntimeException{
    public SenhaIncorretaException(String message) {
        super(message);
    }
}
