package com.baratex.api.service;

import com.baratex.api.model.GaragemVirtual;
import com.baratex.api.repository.GaragemVirtualRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class GaragemVirtualService {

    private final GaragemVirtualRepository repository;

    public GaragemVirtualService(GaragemVirtualRepository repository) {
        this.repository = repository;
    }

    public GaragemVirtual registrar(GaragemVirtual registro) {
        if (registro.getValidadoOficina() != null && registro.getValidadoOficina()) {
            registro.setDataValidacao(LocalDateTime.now());
        }
        return repository.save(registro);
    }

    public List<GaragemVirtual> listarPorVeiculo(UUID veiculoId) {
        return repository.findByAgendamentoVeiculoId(veiculoId);
    }
}