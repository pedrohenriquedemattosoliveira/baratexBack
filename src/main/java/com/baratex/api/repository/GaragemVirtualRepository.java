package com.baratex.api.repository;

import com.baratex.api.model.GaragemVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GaragemVirtualRepository extends JpaRepository<GaragemVirtual, UUID> {
    List<GaragemVirtual> findByAgendamentoVeiculoId(UUID veiculoId);
}