package com.baratex.api.controller;

import com.baratex.api.model.Agendamento;
import com.baratex.api.model.StatusAgendamento;
import com.baratex.api.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody Agendamento agendamento) {
        return ResponseEntity.ok(service.criarAgendamento(agendamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/oficina/{oficinaId}")
    public ResponseEntity<List<Agendamento>> listarPorOficina(@PathVariable UUID oficinaId) {
        return ResponseEntity.ok(service.listarPorOficina(oficinaId));
    }

    @GetMapping("/motorista/{motoristaId}")
    public ResponseEntity<List<Agendamento>> listarPorMotorista(@PathVariable UUID motoristaId) {
        return ResponseEntity.ok(service.listarPorMotorista(motoristaId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Agendamento> atualizarStatus(
            @PathVariable UUID id,
            @RequestParam StatusAgendamento status) {
        return ResponseEntity.ok(service.atualizarStatus(id, status));
    }
}