package com.baratex.api.repository;

import com.baratex.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.email) = LOWER(:identificador) OR CAST(u.id AS string) = :identificador")
    Optional<Usuario> findByIdentificador(@Param("identificador") String identificador);
}