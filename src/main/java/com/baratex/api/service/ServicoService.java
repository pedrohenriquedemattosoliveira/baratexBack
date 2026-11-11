package com.baratex.api.service;

import com.baratex.api.dto.ServicoRequestDTO;
import com.baratex.api.model.Oficina;
import com.baratex.api.model.Servico;
import com.baratex.api.repository.OficinaRepository;
import com.baratex.api.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ServicoService {

    private final ServicoRepository repository;
    private final OficinaRepository oficinaRepository;

    public ServicoService(ServicoRepository repository, OficinaRepository oficinaRepository) {
        this.repository = repository;
        this.oficinaRepository = oficinaRepository;
    }

    @Transactional
    public Servico salvar(ServicoRequestDTO dto) {
        Oficina oficina = oficinaRepository.findById(dto.oficinaId())
                .orElseThrow(() -> new RuntimeException("Oficina não encontrada com o ID: " + dto.oficinaId()));

        Servico servico = new Servico();
        servico.setNome(dto.nome());
        servico.setDescricao(dto.descricao());
        servico.setPrecoBase(dto.preco());
        servico.setTempoEstimadoMinutos(dto.tempoEstimadoMinutos());
        servico.setOficina(oficina);

        return repository.save(servico);
    }

    @Transactional(readOnly = true)
    public List<Servico> listarPorOficina(UUID oficinaId) {
        return repository.findByOficinaId(oficinaId);
    }

    @Transactional(readOnly = true)
    public List<Servico> buscarPorOficinaId(UUID oficinaId) {
        return listarPorOficina(oficinaId);
    }
}