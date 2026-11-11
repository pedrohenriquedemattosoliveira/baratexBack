package com.baratex.api.repository;

import com.baratex.api.model.MensagemChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MensagemChatRepository extends JpaRepository<MensagemChat, UUID> {
    List<MensagemChat> findByAgendamentoIdOrderByEnviadoEmAsc(UUID agendamentoId);

    @Modifying
    @Query("DELETE FROM MensagemChat m WHERE m.agendamento.id = :agendamentoId")
    void deleteByAgendamentoId(@Param("agendamentoId") UUID agendamentoId);
}