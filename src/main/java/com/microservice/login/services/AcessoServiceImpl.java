package com.microservice.login.services;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.domain.acesso.AcessoServiceBase;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.repository.AcessoRepository;
import com.microservice.login.utils.AcessoMapper;
import com.microservice.login.utils.exception.AcessoNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AcessoServiceImpl implements AcessoServiceBase {

    private AcessoRepository acessoRepository;

    private AcessoMapper acessoMapper;

    public AcessoServiceImpl(
            AcessoRepository acessoRepository,
            AcessoMapper acessoMapper) {
        this.acessoRepository = acessoRepository;
        this.acessoMapper = acessoMapper;
    }

    @Override
    public List<Acesso> verUsuariosCadastrados() {
        return acessoRepository.findAll();
    }

    @Override
    @Transactional
    public Acesso adicionarNovoAcesso(@RequestBody AcessoDto novoAcessoDto) {
        try {
            if (novoAcessoDto.getFuncao().equals("admin")) {
//        BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
//        String senhacriptografada = criptografar.encode(novoAcessoDto.getSenha());
//        novoAcessoDto.setSenha(senhacriptografada);
                Acesso novoAcesso = new Acesso(
                        novoAcessoDto.getUsuario(),
                        novoAcessoDto.getSenha(),
                        novoAcessoDto.getFuncao());
                return acessoRepository.save(novoAcesso);
            }
        }
        catch (ResponseStatusException e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public Acesso buscarAcessoPorId(UUID id){
        return acessoRepository.findById(id).orElseThrow(() -> new AcessoNotFoundException(id));
    }



    @Override
    public Acesso atualizarAcesso(UUID id, AcessoDto atualizarAcessoDto){
        AcessoMapper acessoMapper = new AcessoMapper();
        try {
            Acesso acessoAtualizado = acessoMapper.ConverterAacessoDtoParaAcesso(atualizarAcessoDto);
            acessoAtualizado.setId(id);
            acessoRepository.save(acessoAtualizado);
            return acessoAtualizado;
        } catch (RuntimeException e) {
            e.getMessage();

        }
        return null;
    }

        @Override
        public void deletarAcesso (UUID id){
            acessoRepository.deleteById(id);
        }
    
}
