package com.microservice.login.specification.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.microservice.login.domain.acesso.AcessoDAO;
import com.microservice.login.dto.AcessoFiltroUsuarioDTO;
import com.microservice.login.dto.PaginacaoRequestDTO;
import com.microservice.login.specification.LoginSpecification;
import com.microservice.login.utils.QueryUtils;
import com.microservice.login.utils.mappers.AcessoMapper;


public class LoginSpecificationImpl implements LoginSpecification {


    @PersistenceContext
    private EntityManager entityManager;

    private QueryUtils queryUtils;

    public LoginSpecificationImpl(QueryUtils queryUtils) {
        this.queryUtils = queryUtils;
    }

    
    @Override
    @ReadOnlyProperty
    public Page<AcessoDAO> buscarTodosAcessos(PaginacaoRequestDTO paginacaoRequestDTO, AcessoFiltroUsuarioDTO usuarioDTO) {
        TypedQuery<Tuple> query = entityManager.createNamedQuery("Acesso.buscarTodosAcessos", Tuple.class);
        List<Tuple> result = query
                    .setParameter("usuario", usuarioDTO.getUsuario())
                    // .setParameter("sort", paginacaoRequestDTO.getSort())
                    .setFirstResult(paginacaoRequestDTO.getOffset())
                    .setMaxResults(paginacaoRequestDTO.getLimit())
                    .getResultList();
                    
        return new PageImpl<>(AcessoMapper.converterListaTuplaEmListaAcessoDAO(result), PageRequest.of(paginacaoRequestDTO.getOffset() / paginacaoRequestDTO.getLimit(), paginacaoRequestDTO.getLimit()), contagemTodosAcessos(usuarioDTO));
        
    }


    public Integer contagemTodosAcessos(AcessoFiltroUsuarioDTO usuarioDTO) {
        Query query = entityManager.createNamedQuery("Acesso.contagemTodosAcessos");
        query.setParameter("usuario", queryUtils.parametroContemOValor(usuarioDTO.getUsuario()));

        return ((BigInteger) query.getSingleResult()).intValue();
    }
    
}
