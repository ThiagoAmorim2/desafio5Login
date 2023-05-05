package com.microservice.login.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.login.domain.acesso.AcessoDAO;
import com.microservice.login.dto.AcessoDTO;
import com.microservice.login.dto.AcessoFiltroUsuarioDTO;
import com.microservice.login.dto.AcessoResponseDTO;
import com.microservice.login.dto.ListaGenericaResponseDTO;
import com.microservice.login.dto.PaginacaoRequestDTO;
import com.microservice.login.utils.exception.UsuarioNaoAdminException;

@Service
public interface AcessoServiceBase {
    // List<AcessoDAO> verUsuariosCadastrados(Long id) throws UsuarioNaoAdminException;
    public ListaGenericaResponseDTO<AcessoResponseDTO> verUsuariosCadastrados(Long id, PaginacaoRequestDTO paginacao, AcessoFiltroUsuarioDTO filtro) throws UsuarioNaoAdminException;

    AcessoDTO adicionarNovoAcesso(AcessoDTO novoAcessoDto);

    AcessoDTO atualizarAcesso(Long idPermissao, AcessoDTO acessoParaAtualizarDto) throws UsuarioNaoAdminException;

    String deletarAcesso(Long id, AcessoDTO acessoDto) throws UsuarioNaoAdminException;

    AcessoDAO buscarAcessoPorId(Long id);


}
