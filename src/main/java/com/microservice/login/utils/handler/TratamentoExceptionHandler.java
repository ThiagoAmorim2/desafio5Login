package com.microservice.login.utils.handler;

import com.microservice.login.utils.exception.AcessoNotFoundException;
import com.microservice.login.utils.exception.SenhaIncorretaException;
import com.microservice.login.utils.exception.UsuarioNaoAdminException;
import com.microservice.login.utils.exception.UsuarioNaoExisteException;
import com.microservice.login.utils.exception.detalhes.ExceptionsDetalhe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class TratamentoExceptionHandler {
    @ExceptionHandler(UsuarioNaoExisteException.class)
    public ResponseEntity<?> handleUsuarioNaoExisteException(UsuarioNaoExisteException usuarioNaoExisteException){
        ExceptionsDetalhe exceptionsDetalhe = ExceptionsDetalhe
                .ExceptionsDetalheBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .detalhe(usuarioNaoExisteException.getMessage())
                .mensagemDesenvolvedor(usuarioNaoExisteException.getClass().getName())
                .build();
        return new ResponseEntity<>(exceptionsDetalhe, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsuarioNaoAdminException.class)
    public ResponseEntity<?> handleUsuarioNaoAdminException(UsuarioNaoAdminException usuarioNaoAdminException){
        ExceptionsDetalhe exceptionsDetalhe = ExceptionsDetalhe
                .ExceptionsDetalheBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .detalhe(usuarioNaoAdminException.getMessage())
                .mensagemDesenvolvedor(usuarioNaoAdminException.getClass().getName())
                .build();
        return new ResponseEntity<>(exceptionsDetalhe, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AcessoNotFoundException.class)
    public ResponseEntity<?> handleAcessoNotFoundException(AcessoNotFoundException acessoNotFoundException){
        ExceptionsDetalhe exceptionsDetalhe = ExceptionsDetalhe
                .ExceptionsDetalheBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .detalhe(acessoNotFoundException.getMessage())
                .mensagemDesenvolvedor(acessoNotFoundException.getClass().getName())
                .build();
        return new ResponseEntity<>(exceptionsDetalhe, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SenhaIncorretaException.class)
    public ResponseEntity<?> handleSenhaIncorretaException(SenhaIncorretaException senhaIncorretaException){
        ExceptionsDetalhe exceptionsDetalhe = ExceptionsDetalhe
                .ExceptionsDetalheBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .detalhe(senhaIncorretaException.getMessage())
                .mensagemDesenvolvedor(senhaIncorretaException.getClass().getName())
                .build();
        return new ResponseEntity<>(exceptionsDetalhe, HttpStatus.BAD_REQUEST);
    }
}
