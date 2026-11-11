package com.baratex.api.repository;

import com.baratex.api.model.ModeloVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ModeloVeiculoRepository extends JpaRepository<ModeloVeiculo, UUID> {

    List<ModeloVeiculo> findByMarcaIdAndNomeModeloContainingIgnoreCaseOrderByNomeModeloAsc(UUID marcaId, String nomeModelo);

    List<ModeloVeiculo> findByMarcaIdOrderByNomeModeloAsc(UUID marcaId);
}