package com.microservice.login.domain.acesso;

import com.microservice.login.services.AcessoServiceBase;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.repository.AcessoRepository;
import com.microservice.login.utils.exception.AcessoNotFoundException;
import com.microservice.login.utils.exception.UsuarioNaoAdminException;
import com.microservice.login.utils.mappers.AcessoMapper;
import com.microservice.login.utils.validacoes.ValidacaoUsuarioUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AcessoServiceImpl implements AcessoServiceBase {

    private final AcessoRepository acessoRepository;
    private final ValidacaoUsuarioUtils validacaoUsuarioUtils;
    private final AcessoMapper acessoMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AcessoServiceImpl(
            AcessoRepository acessoRepository,
            ValidacaoUsuarioUtils validacaoUsuarioUtils,
            AcessoMapper acessoMapper){
        this.acessoRepository = acessoRepository;
        this.validacaoUsuarioUtils = validacaoUsuarioUtils;
        this.acessoMapper = acessoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Acesso> verUsuariosCadastrados(UUID id) throws UsuarioNaoAdminException {
        Acesso acessoId = buscarAcessoPorId(id);
        if(validacaoUsuarioUtils.ehUmUsuarioValido(acessoId)) {
            return acessoRepository.findAll();
        }//FALTA TRATAR EXCEÇÃO
        throw new UsuarioNaoAdminException("Usuário não possui esse nível de acesso");
    }

    @Override
    @Transactional
    public AcessoDto adicionarNovoAcesso(AcessoDto novoAcessoDto) {
        //Criptografia da senha
        String senhacriptografada = this.passwordEncoder.encode(novoAcessoDto.getSenha());

        //Criação do model
        Acesso novoAcesso = acessoMapper.converterAcessoDtoEmAcesso(novoAcessoDto);
        novoAcesso.setSenha(senhacriptografada);

        //Salvar
            acessoRepository.save(novoAcesso);
            AcessoDto acessoDto = acessoMapper.converterAcessoEmAcessoDto(novoAcesso);
            return acessoDto;
    }

    @Override
    public Acesso buscarAcessoPorId(UUID id){
        return acessoRepository.findById(id).orElseThrow(() -> new AcessoNotFoundException(id));
    }

    @Override
    @Transactional
    public AcessoDto atualizarAcesso(UUID id, AcessoDto acessoParaAtualizarDto) throws UsuarioNaoAdminException {
        Acesso acessoIdPermissao = buscarAcessoPorId(id);
        if (validacaoUsuarioUtils.ehUmUsuarioValido(acessoIdPermissao)) {
            Acesso acessoParaAtualizar = new Acesso();
            acessoParaAtualizar.setId(acessoParaAtualizarDto.getId());
            acessoParaAtualizar.setNomeUsuario(acessoParaAtualizarDto.getUsuario());
            acessoParaAtualizar.setSenha(acessoParaAtualizarDto.getSenha());
            acessoParaAtualizar.setFuncao(acessoParaAtualizarDto.getFuncao());
            acessoRepository.save(acessoParaAtualizar);
            AcessoDto acessoAtualizadoDto = acessoMapper.converterAcessoEmAcessoDto(acessoParaAtualizar);
            return acessoAtualizadoDto;
        }
        throw new UsuarioNaoAdminException("Usuário não possui esse nível de acesso");
    }

        @Override
        public String deletarAcesso (UUID id, AcessoDto acessoDto) throws UsuarioNaoAdminException {
            Acesso acessoIdPermissao = buscarAcessoPorId(id);
            if (validacaoUsuarioUtils.ehUmUsuarioValido(acessoIdPermissao)) {
                Acesso acessoParaDeletar = acessoMapper.converterAcessoDtoEmAcesso(acessoDto);
                var uuid = acessoParaDeletar.getId();
                acessoRepository.deleteById(uuid);
                return "Usuário deletado com sucesso!";
            }
            //UTILIZAR ENUMS
            throw new UsuarioNaoAdminException("Usuário não possui esse nível de acesso");

        }
    
}
