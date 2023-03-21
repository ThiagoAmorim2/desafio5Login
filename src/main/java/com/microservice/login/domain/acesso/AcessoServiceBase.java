package com.microservice.login.domain.acesso;

import com.microservice.login.dto.AcessoDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AcessoServiceBase {
    List<Acesso> verUsuariosCadastrados();

    Acesso adicionarNovoAcesso(@RequestBody AcessoDto novoAcessoDto);
}
