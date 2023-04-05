package com.microservice.login.utils.mappers;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;
import org.springframework.stereotype.Component;

@Component
public class AcessoMapper {
    public AcessoDto converterAcessoEmAcessoDto(Acesso acesso){
        AcessoDto acessoDto = new AcessoDto();
        acessoDto.setId(acesso.getId());
        acessoDto.setUsuario(acesso.getNomeUsuario());
        acessoDto.setSenha(acesso.getSenha());
        acessoDto.setFuncao(acesso.getFuncao());
        return acessoDto;
    };

    public Acesso converterAcessoDtoEmAcesso(AcessoDto acessoDto){
        Acesso acesso = new Acesso();
        acesso.setId(acessoDto.getId());
        acesso.setNomeUsuario(acessoDto.getUsuario());
        acesso.setSenha(acessoDto.getSenha());
        acesso.setFuncao(acessoDto.getFuncao());
        return acesso;
    }
}
