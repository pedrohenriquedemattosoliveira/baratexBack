package com.baratex.api.repository;

import com.baratex.api.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, UUID> {

    @Query("SELECT m FROM Motorista m WHERE LOWER(m.usuario.email) = LOWER(:identificador) OR CAST(m.usuario.id AS string) = :identificador")
    Optional<Motorista> findByIdentificadorUsuario(@Param("identificador") String identificador);
}