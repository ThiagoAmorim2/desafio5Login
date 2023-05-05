package com.microservice.login.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessoFiltroUsuarioDTO {
    
    @NotNull
    private String usuario;

    public AcessoFiltroUsuarioDTO(@NotNull String usuario) {
        this.usuario = usuario;
    }

    

    
}
