package com.microservice.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessoResponseDTO {

    private Long id;

    private String usuario;

    private String senha;

    private String funcao;


}
