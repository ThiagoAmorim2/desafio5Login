package com.microservice.login.services;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.repository.AcessoRepository;
import com.microservice.login.utils.mappers.AcessoMapper;
import org.springframework.stereotype.Service;

@Service
public class SenhaServiceImpl {
    private final AcessoRepository acessoRepository;
    private final AcessoServiceImpl acessoService;
    private final AcessoMapper acessoMapper;

    public SenhaServiceImpl(AcessoRepository acessoRepository, AcessoServiceImpl acessoService, AcessoMapper acessoMapper) {
        this.acessoRepository = acessoRepository;
        this.acessoService = acessoService;
        this.acessoMapper = acessoMapper;
    }


    public String validarSenha(AcessoDto acessoDto){
        var usuarioDto = acessoDto.getUsuario();
        var senhaDto = acessoDto.getSenha();
        Acesso acessoParaVerificarUsuarioSenha = acessoMapper.converterAcessoDtoEmAcesso(acessoDto);
        Acesso idParaVerificarUsuariSenha = acessoService.buscarAcessoPorId(acessoParaVerificarUsuarioSenha.getId());
        var usuarioDoBanco = idParaVerificarUsuariSenha.getUsuario();
        var senhaDoBanco = idParaVerificarUsuariSenha.getSenha();

        if(usuarioDoBanco.equals(usuarioDto) && senhaDoBanco.equals(senhaDto)){
            return "Usuário Logado";
        }
        return "Usuário ou senha inválidos";

    }
}
