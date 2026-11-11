package com.baratex.api.controller;

import com.baratex.api.model.Avaliacao;
import com.baratex.api.service.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    private final AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criar(@RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(service.salvar(avaliacao));
    }

    @GetMapping("/oficina/{oficinaId}")
    public ResponseEntity<List<Avaliacao>> listarPorOficina(@PathVariable UUID oficinaId) {
        return ResponseEntity.ok(service.listarPorOficina(oficinaId));
    }
}