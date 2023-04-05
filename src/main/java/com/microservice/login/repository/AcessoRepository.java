package com.microservice.login.repository;

import com.microservice.login.domain.acesso.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, UUID> {
    Acesso findByNomeUsuario(String nomeUsuario);
}
