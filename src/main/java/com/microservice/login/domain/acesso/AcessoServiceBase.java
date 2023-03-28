package com.microservice.login.domain.acesso;

import com.microservice.login.dto.AcessoDto;
import com.microservice.login.utils.exception.UsuarioNaoAdminException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface AcessoServiceBase {
    List<Acesso> verUsuariosCadastrados(AcessoDto acessoDto) throws UsuarioNaoAdminException;

    Acesso adicionarNovoAcesso(AcessoDto novoAcessoDto);

    Acesso atualizarAcesso(UUID id, AcessoDto atualizarAcessoDto);

    void deletarAcesso(UUID id);

    Acesso buscarAcessoPorId(UUID id);


}
