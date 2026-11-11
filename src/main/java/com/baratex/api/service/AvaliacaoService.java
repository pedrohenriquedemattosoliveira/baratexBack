package com.baratex.api.service;

import com.baratex.api.model.Avaliacao;
import com.baratex.api.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository repository;

    public AvaliacaoService(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    public Avaliacao salvar(Avaliacao avaliacao) {
        if (avaliacao.getNota() < 1 || avaliacao.getNota() > 5) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 5.");
        }
        return repository.save(avaliacao);
    }

    public List<Avaliacao> listarPorOficina(UUID oficinaId) {
        return repository.findByAgendamentoOficinaId(oficinaId);
    }
}