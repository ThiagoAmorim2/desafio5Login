package com.microservice.login.services;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;

public interface AcessoSenhaServiceBase {

    Acesso buscarPorNome(String nomeUsuario);

    String validarSenha(AcessoDto acessoParaValidarLogin);
}
