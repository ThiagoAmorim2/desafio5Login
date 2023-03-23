package com.microservice.login.utils;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;
import org.springframework.stereotype.Component;

@Component
public class AcessoMapper {
    public AcessoDto converterAcessoEmAcessoDto(Acesso acesso){
        AcessoDto acessoDto = new AcessoDto();
        acessoDto.setUsuario(acesso.getUsuario());
        acessoDto.setSenha(acesso.getSenha());
        acessoDto.setFuncao(acesso.getFuncao());
        return acessoDto;
    };

    public Acesso ConverterAacessoDtoParaAcesso(AcessoDto acessoDto){
        Acesso acesso = new Acesso();
        acesso.setUsuario(acessoDto.getUsuario());
        acesso.setSenha(acessoDto.getSenha());
        acesso.setFuncao(acessoDto.getFuncao());
        return acesso;
    }
}