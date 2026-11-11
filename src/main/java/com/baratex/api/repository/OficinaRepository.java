package com.baratex.api.repository;

import com.baratex.api.model.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, UUID> {

    @Query(value = "SELECT o.* FROM oficinas o " +
            "JOIN enderecos e ON o.endereco_id = e.id " +
            "WHERE ST_DWithin(e.localizacao, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326), :raioMeters, true)",
            nativeQuery = true)
    List<Oficina> buscarProximosNative(@Param("lat") Double latitude,
                                       @Param("lng") Double longitude,
                                       @Param("raioMeters") Double raioMeters);
}