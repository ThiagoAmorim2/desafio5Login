package com.microservice.login.services;

import com.microservice.login.domain.acesso.AcessoDAO;
import com.microservice.login.dto.AcessoDTO;

public interface AcessoSenhaServiceBase {

    AcessoDAO buscarPorNome(String nomeUsuario);

    String validarSenha(AcessoDTO acessoParaValidarLogin);
}
