package com.microservice.login.services;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.domain.acesso.AcessoServiceBase;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.repository.AcessoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AcessoServiceImpl implements AcessoServiceBase {

    private AcessoRepository acessoRepository;

    public AcessoServiceImpl(AcessoRepository acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    @Override
    public List<Acesso> verUsuariosCadastrados() {
        return acessoRepository.findAll();
    }

    @Override
    @Transactional
    public Acesso adicionarNovoAcesso(@RequestBody AcessoDto novoAcessoDto) {
        Acesso novoAcesso = new Acesso(novoAcessoDto.getUsuario(), novoAcessoDto.getSenha(), novoAcessoDto.getSenha());
        return acessoRepository.save(novoAcesso);
    }

}
