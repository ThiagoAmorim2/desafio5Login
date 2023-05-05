package com.microservice.login.dto;

import org.springframework.lang.NonNull;

public class AcessoFiltroUsuarioDTO {
    
    @NonNull
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
}
