package com.microservice.login.domain.acesso;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.login.domain.mapper.ListaGenericaResponseDTOMapper;
import com.microservice.login.dto.AcessoDTO;
import com.microservice.login.dto.AcessoFiltroUsuarioDTO;
import com.microservice.login.dto.AcessoResponseDTO;
import com.microservice.login.dto.ListaGenericaResponseDTO;
import com.microservice.login.dto.PaginacaoRequestDTO;
import com.microservice.login.repository.AcessoRepository;
import com.microservice.login.services.AcessoServiceBase;
import com.microservice.login.utils.exception.AcessoNotFoundException;
import com.microservice.login.utils.exception.UsuarioNaoAdminException;
import com.microservice.login.utils.mappers.AcessoMapper;
import com.microservice.login.utils.validacoes.ValidacaoUsuarioUtils;

@Service
public class AcessoServiceImpl implements AcessoServiceBase {

    private final AcessoRepository acessoRepository;
    private final ValidacaoUsuarioUtils validacaoUsuarioUtils;
    private final AcessoMapper acessoMapper;
    private final ListaGenericaResponseDTOMapper listaGenericaResponseDTOMapper;

    private AcessoSenhaServiceImpl acessoSenhaService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AcessoServiceImpl(
            AcessoRepository acessoRepository,
            ValidacaoUsuarioUtils validacaoUsuarioUtils,
            AcessoMapper acessoMapper,
            ListaGenericaResponseDTOMapper listaGenericaResponseDTOMapper,
            AcessoSenhaServiceImpl acessoSenhaService){
        this.acessoRepository = acessoRepository;
        this.validacaoUsuarioUtils = validacaoUsuarioUtils;
        this.acessoMapper = acessoMapper;
        this.acessoSenhaService = acessoSenhaService;
        this.listaGenericaResponseDTOMapper = listaGenericaResponseDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ListaGenericaResponseDTO<AcessoResponseDTO> verUsuariosCadastrados(Long id, PaginacaoRequestDTO paginacao, AcessoFiltroUsuarioDTO filtro) throws UsuarioNaoAdminException {
        AcessoDAO acessoId = buscarAcessoPorId(id);
        if(validacaoUsuarioUtils.ehUmUsuarioValido(acessoId)) {
            paginacao.setLimit(20);
            paginacao.setOffset(0);
            Page<AcessoDAO> acessosPage = acessoRepository.buscarTodosAcessos(paginacao, filtro);
            return listaGenericaResponseDTOMapper.fromPage(acessosPage, AcessoResponseDTO.class);
        }
        throw new UsuarioNaoAdminException("Usuário " + acessoId.getNomeUsuario() + " não possui esse nível de acesso");
    }

    @Override
    @Transactional
    public AcessoDTO adicionarNovoAcesso(AcessoDTO novoAcessoDto) {
        //Criptografia da senha
        String senhacriptografada = this.passwordEncoder.encode(novoAcessoDto.getSenha());

        //Criação do model
        AcessoDAO novoAcesso = acessoMapper.converterAcessoDtoEmAcesso(novoAcessoDto);
        novoAcesso.setSenha(senhacriptografada);

        //Salvar
            acessoRepository.save(novoAcesso);
            AcessoDTO acessoDto = acessoMapper.converterAcessoEmAcessoDto(novoAcesso);
            return acessoDto;
    }

    @Override
    public AcessoDAO buscarAcessoPorId(Long id){
        return acessoRepository.findById(id).orElseThrow(() -> new AcessoNotFoundException(id));
    }

    @Override
    @Transactional
    public AcessoDTO atualizarAcesso(Long id, AcessoDTO acessoParaAtualizarDto) throws UsuarioNaoAdminException {
        AcessoDAO acessoIdPermissao = buscarAcessoPorId(id);
        if (validacaoUsuarioUtils.ehUmUsuarioValido(acessoIdPermissao)) {
            AcessoDAO acessoParaAtualizar = new AcessoDAO();
            acessoParaAtualizar.setId(acessoParaAtualizarDto.getId());
            acessoParaAtualizar.setNomeUsuario(acessoParaAtualizarDto.getUsuario());
            acessoParaAtualizar.setSenha(acessoParaAtualizarDto.getSenha());
            acessoParaAtualizar.setFuncao(acessoParaAtualizarDto.getFuncao());
            acessoRepository.save(acessoParaAtualizar);
            AcessoDTO acessoAtualizadoDto = acessoMapper.converterAcessoEmAcessoDto(acessoParaAtualizar);
            return acessoAtualizadoDto;
        }
        throw new UsuarioNaoAdminException("Usuário " + acessoIdPermissao.getNomeUsuario() + " não possui esse nível de acesso");
    }

        @Override
        public String deletarAcesso (Long id, AcessoDTO acessoDto) throws UsuarioNaoAdminException {
            AcessoDAO acessoIdPermissao = buscarAcessoPorId(id);
            if (validacaoUsuarioUtils.ehUmUsuarioValido(acessoIdPermissao)) {
                AcessoDAO acessoParaDeletar = acessoMapper.converterAcessoDtoEmAcesso(acessoDto);
                var Long = acessoParaDeletar.getId();
                acessoRepository.deleteById(Long);
                return "Usuário deletado com sucesso!";
            }
            //UTILIZAR ENUMS
            throw new UsuarioNaoAdminException("Usuário " + acessoIdPermissao.getNomeUsuario() + " não possui esse nível de acesso");

        }
    
}
