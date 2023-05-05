package com.microservice.login.dto;

import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessoFiltroUsuarioDTO {
    
    @NonNull
    private String usuario;

    public AcessoFiltroUsuarioDTO(@NotNull String usuario) {
        this.usuario = usuario;
    }

    

    
}
