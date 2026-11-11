// 1. Novo: MarcaRepository.java
package com.baratex.api.repository;
import com.baratex.api.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MarcaRepository extends JpaRepository<Marca, UUID> {
    List<Marca> findByNomeContainingIgnoreCaseOrderByNome(String nome);
}