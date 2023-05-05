package com.microservice.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.login.domain.acesso.AcessoDAO;
import com.microservice.login.specification.LoginSpecification;

@Repository
public interface AcessoRepository extends JpaRepository<AcessoDAO, Long>, LoginSpecification {
    AcessoDAO findByUsuario(String usuario);
}
