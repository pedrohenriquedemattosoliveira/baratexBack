package com.baratex.api.service;

import com.baratex.api.model.MensagemChat;
import com.baratex.api.repository.MensagemChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MensagemChatService {

    private final MensagemChatRepository repository;

    public MensagemChatService(MensagemChatRepository repository) {
        this.repository = repository;
    }

    public MensagemChat enviarMensagem(MensagemChat mensagem) {
        return repository.save(mensagem);
    }

    public List<MensagemChat> listarPorAgendamento(UUID agendamentoId) {
        return repository.findByAgendamentoIdOrderByEnviadoEmAsc(agendamentoId);
    }

    @Transactional
    public void apagarHistoricoPorAgendamento(UUID agendamentoId) {
        repository.deleteByAgendamentoId(agendamentoId);
    }
}