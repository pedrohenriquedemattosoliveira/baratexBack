package com.baratex.api.controller;

import com.baratex.api.dto.ServicoRequestDTO;
import com.baratex.api.model.Servico;
import com.baratex.api.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "*")
public class ServicoController {

    private final ServicoService service;

    public ServicoController(ServicoService service) {
        this.service = service;
    }

    @GetMapping("/oficina/{oficinaId}")
    public ResponseEntity<List<Servico>> listarPorOficina(@PathVariable UUID oficinaId) {
        return ResponseEntity.ok(service.listarPorOficina(oficinaId));
    }

    @PostMapping
    public ResponseEntity<Servico> criar(@RequestBody @Valid ServicoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }
}