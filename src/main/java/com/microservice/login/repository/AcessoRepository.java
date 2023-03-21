package com.microservice.login.repository;

import com.microservice.login.domain.acesso.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AcessoRepository extends JpaRepository<Acesso, UUID> {
}
