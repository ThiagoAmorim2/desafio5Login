package com.microservice.login.specification;

import org.springframework.data.domain.Page;

import com.microservice.login.domain.acesso.AcessoDAO;
import com.microservice.login.dto.AcessoFiltroUsuarioDTO;
import com.microservice.login.dto.PaginacaoRequestDTO;

public interface LoginSpecification {

    public Page<AcessoDAO> buscarTodosAcessos(PaginacaoRequestDTO paginacaoRequestDTO, AcessoFiltroUsuarioDTO usuarioDTO);
    public Integer contagemTodosAcessos(AcessoFiltroUsuarioDTO usuarioDTO);
}
