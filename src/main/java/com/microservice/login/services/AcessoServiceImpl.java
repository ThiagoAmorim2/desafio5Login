package com.microservice.login.services;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.domain.acesso.AcessoServiceBase;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.repository.AcessoRepository;
import com.microservice.login.utils.AcessoMapper;
import com.microservice.login.utils.exception.AcessoNotFoundException;
import com.microservice.login.utils.exception.UsuarioNaoAdminException;
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
    public List<Acesso> verUsuariosCadastrados(AcessoDto acessoDto) throws UsuarioNaoAdminException {
        if(acessoDto.getFuncao().equals("admin")) {
            return acessoRepository.findAll();
        }
        throw new UsuarioNaoAdminException("Usuário sem permissão");
    }

    @Override
    @Transactional
    public Acesso adicionarNovoAcesso(@RequestBody AcessoDto novoAcessoDto) {
//        BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
//        String senhacriptografada = criptografar.encode(novoAcessoDto.getSenha());
//        novoAcessoDto.setSenha(senhacriptografada);
            Acesso novoAcesso = new Acesso(
                    novoAcessoDto.getUsuario(),
                    novoAcessoDto.getSenha(),
                    novoAcessoDto.getFuncao());
            return acessoRepository.save(novoAcesso);
    }

//    public boolean eUmUsuarioValido(AcessoDto acessoDto){
//        UUID id = acessoDto.getId();
//        buscarAcessoPorId(id)
//        acessoDto.getFuncao().equals(ac)
//    }

    @Override
    public Acesso buscarAcessoPorId(UUID id){
        return acessoRepository.findById(id).orElseThrow(() -> new AcessoNotFoundException(id));
    }



    @Override
    public Acesso atualizarAcesso(UUID id, AcessoDto atualizarAcessoDto){
        if(buscarAcessoPorId(atualizarAcessoDto.getId()).equals(id)) {
            Acesso acessoAtualizado = acessoMapper.ConverterAacessoDtoParaAcesso(atualizarAcessoDto);
            acessoAtualizado.setId(id);
            acessoRepository.save(acessoAtualizado);
            return acessoAtualizado;
        }
        throw new AcessoNotFoundException(id);
    }

        @Override
        public void deletarAcesso (UUID id){
            acessoRepository.deleteById(id);
        }
    
}
