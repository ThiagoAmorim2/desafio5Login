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

    public Acesso buscarPorNome(String usuario){
        return acessoRepository.findByUsuario(usuario);
    }
    public String validarSenha(AcessoDto acessoParaValidarLogin){
        Acesso usuarioDoBancoDados = buscarPorNome(acessoParaValidarLogin.getUsuario());

        var usuarioDoBanco = usuarioDoBancoDados.getUsuario();
        var senhaDoBanco = usuarioDoBancoDados.getSenha();

        if(usuarioDoBanco.equals(acessoParaValidarLogin.getUsuario()) && senhaDoBanco.equals(acessoParaValidarLogin.getSenha())){
            return "Usuário Logado";
        }
        return "Usuário ou senha inválidos";

    }
}
