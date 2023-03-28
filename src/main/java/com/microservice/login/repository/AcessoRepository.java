package com.microservice.login.repository;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AcessoRepository extends JpaRepository<Acesso, UUID> {
//    Optional<AcessoDto> findByLogin(String username);
    Optional<AcessoDto> findByUsuario(String usuario);
}
