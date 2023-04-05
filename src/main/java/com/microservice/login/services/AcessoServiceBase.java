package com.microservice.login.services;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.utils.exception.UsuarioNaoAdminException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AcessoServiceBase {
    List<Acesso> verUsuariosCadastrados(UUID id) throws UsuarioNaoAdminException;

    AcessoDto adicionarNovoAcesso(AcessoDto novoAcessoDto);

    AcessoDto atualizarAcesso(UUID idPermissao, AcessoDto acessoParaAtualizarDto) throws UsuarioNaoAdminException;

    String deletarAcesso(UUID id, AcessoDto acessoDto) throws UsuarioNaoAdminException;

    Acesso buscarAcessoPorId(UUID id);


}
