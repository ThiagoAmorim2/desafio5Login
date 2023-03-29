package com.microservice.login.services;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.domain.acesso.AcessoServiceBase;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.repository.AcessoRepository;
import com.microservice.login.utils.AcessoMapper;
import com.microservice.login.utils.ValidacaoUsuarioUtils;
import com.microservice.login.utils.exception.AcessoNotFoundException;
import com.microservice.login.utils.exception.UsuarioNaoAdminException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AcessoServiceImpl implements AcessoServiceBase {

    private final AcessoRepository acessoRepository;

    private final ValidacaoUsuarioUtils validacaoUsuarioUtils;
    private final AcessoMapper acessoMapper;

    public AcessoServiceImpl(
            AcessoRepository acessoRepository,
            ValidacaoUsuarioUtils validacaoUsuarioUtils, AcessoMapper acessoMapper) {
        this.acessoRepository = acessoRepository;
        this.validacaoUsuarioUtils = validacaoUsuarioUtils;
        this.acessoMapper = acessoMapper;
    }

    @Override
    public List<Acesso> verUsuariosCadastrados(UUID id) throws UsuarioNaoAdminException {
        Acesso acessoId = buscarAcessoPorId(id);
        if(validacaoUsuarioUtils.ehUmUsuarioValido(acessoId)) {
            return acessoRepository.findAll();
        }
        throw new UsuarioNaoAdminException("Usuário sem permissão");
    }

    @Override
    @Transactional
    public AcessoDto adicionarNovoAcesso(AcessoDto novoAcessoDto) {
//        BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
//        String senhacriptografada = criptografar.encode(novoAcesso.getSenha());
//        novoAcesso.setSenha(senhacriptografada);
            Acesso novoAcesso = new Acesso(
                    novoAcessoDto.getUsuario(),
                    novoAcessoDto.getSenha(),
                    novoAcessoDto.getFuncao());
            acessoRepository.save(novoAcesso);
            AcessoDto acessoDto = acessoMapper.converterAcessoEmAcessoDto(novoAcesso);
            return acessoDto;
    }


    @Override
    public Acesso buscarAcessoPorId(UUID id){
        return acessoRepository.findById(id).orElseThrow(() -> new AcessoNotFoundException(id));
    }



//    @Override
//    public AcessoDto atualizarAcesso(UUID id, AcessoDto atualizarAcessoDto){
//        if(validacaoUsuarioUtils.ehUmUsuarioValido(atualizarAcessoDto)) {
//            Acesso acessoAtualizado = acessoMapper.ConverterAacessoDtoParaAcesso(atualizarAcessoDto);
//            acessoAtualizado.setId(id);
//            acessoRepository.save(acessoAtualizado);
//            return acessoAtualizado;
//        }
//        throw new AcessoNotFoundException(id);
//    }



    //passar o idPermissão no adiconarAcesso, caso seja admin
    @Override
    @Transactional
    public AcessoDto atualizarAcesso(UUID id, AcessoDto acessoParaAtualizarDto) throws UsuarioNaoAdminException {
        Acesso acessoIdPermissao = buscarAcessoPorId(id);
        if (validacaoUsuarioUtils.ehUmUsuarioValido(acessoIdPermissao)) {
            Acesso acessoIdAtualizacao = buscarAcessoPorId(acessoParaAtualizarDto.getId());
            Acesso acessoAtualizado = new Acesso();
                acessoAtualizado.setId(acessoParaAtualizarDto.getId());
                acessoAtualizado.setUsuario(acessoParaAtualizarDto.getUsuario());
                acessoAtualizado.setSenha(acessoParaAtualizarDto.getSenha());
                acessoAtualizado.setFuncao(acessoParaAtualizarDto.getFuncao());
            acessoRepository.save(acessoAtualizado);
            AcessoDto acessoAtualizadoDto = acessoMapper.converterAcessoEmAcessoDto(acessoAtualizado);
            return acessoAtualizadoDto;
        }
        throw new UsuarioNaoAdminException("Usuário sem permissão");
    }


    //VERIFICAR O DELETE
        @Override
        public void deletarAcesso (UUID id, AcessoDto acessoDto) throws UsuarioNaoAdminException {
            Acesso acessoIdPermissao = buscarAcessoPorId(id);
            if (validacaoUsuarioUtils.ehUmUsuarioValido(acessoIdPermissao)) {
                Acesso acessoParaDeletar = acessoMapper.ConverterAacessoDtoParaAcesso(acessoDto);
                var uuid = acessoParaDeletar.getId();
                acessoRepository.deleteById(uuid);
            }
            throw new UsuarioNaoAdminException("Usuário sem permissão");

        }
    
}
