package com.microservice.login.services;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.domain.acesso.AcessoServiceBase;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.repository.AcessoRepository;
import com.microservice.login.utils.AcessoMapper;
import com.microservice.login.utils.AcessoValidacaoUsuarioUsuarioImpl;
import com.microservice.login.utils.exception.AcessoNotFoundException;
import com.microservice.login.utils.exception.ValidacaoFuncaoException;
import com.microservice.login.utils.exception.ValidacaoSenhaException;
import com.microservice.login.utils.exception.ValidacaoUsuarioException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AcessoServiceImpl implements AcessoServiceBase {

    private AcessoRepository acessoRepository;

    private AcessoMapper acessoMapper;
    private AcessoValidacaoUsuarioUsuarioImpl acessoValidacao;

    public AcessoServiceImpl(
            AcessoRepository acessoRepository,
            AcessoValidacaoUsuarioUsuarioImpl acessoValidacao,
            AcessoMapper acessoMapper) {
        this.acessoRepository = acessoRepository;
        this.acessoValidacao = acessoValidacao;
        this.acessoMapper = acessoMapper;
    }

    @Override
    public List<Acesso> verUsuariosCadastrados() {
        return acessoRepository.findAll();
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

    public Acesso buscarAcessoPorId(UUID id){
//        acessoMapper.converterAcessoEmAcessoDto(acesso);
        return acessoRepository.findById(id).orElseThrow(() -> new AcessoNotFoundException(id));
    }


    public Acesso atualizarAcesso(UUID id, Acesso acesso) throws ValidacaoUsuarioException, ValidacaoFuncaoException, ValidacaoSenhaException {
        AcessoMapper acessoMapper = new AcessoMapper();
        try{
            AcessoDto acessoAtualizadoDto = acessoMapper.converterAcessoEmAcessoDto(acesso);

            //Validações
            var usuario = acessoAtualizadoDto.getUsuario();
            acessoValidacao.validaAtualizacaoUsuario(usuario);
            var senha = acessoAtualizadoDto.getSenha();
            acessoValidacao.validaAtualizacaoSenha(senha);
            var funcao = acessoAtualizadoDto.getFuncao();
            acessoValidacao.validaAtualizacaoFuncao(funcao);

            Acesso acessoAtualizado = acessoMapper.ConverterAacessoDtoParaAcesso(acessoAtualizadoDto);
            acessoAtualizado.setId(id);
            acessoRepository.save(acessoAtualizado);
            return acessoAtualizado;
        }
        catch (ValidacaoUsuarioException e){
            e.getMessage();
        }
        catch (ValidacaoSenhaException e){
            e.getMessage();
        }
        catch (ValidacaoFuncaoException e){
            e.getMessage();
        }
        finally {
            return acesso;
        }
    }

    @Override
    public void deletarAcesso(UUID id) {
        acessoRepository.deleteById(id);
    }

}
