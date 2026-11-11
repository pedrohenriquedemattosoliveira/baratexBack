package com.baratex.api.service;

import com.baratex.api.model.Oficina;
import com.baratex.api.repository.OficinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OficinaService {

    private final OficinaRepository repository;

    public OficinaService(OficinaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Oficina> listarTodas() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Oficina buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oficina não encontrada com o ID: " + id));
    }

    @Transactional
    public Oficina salvar(Oficina oficina) {
        return repository.save(oficina);
    }

    @Transactional(readOnly = true)
    public List<Oficina> buscarProximos(Double latitude, Double longitude, Double raioKm) {
        Double raioMetros = raioKm * 1000.0;
        return repository.buscarProximosNative(latitude, longitude, raioMetros);
    }
}