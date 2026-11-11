package com.baratex.api.controller;

import com.baratex.api.model.GaragemVirtual;
import com.baratex.api.service.GaragemVirtualService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/garagem-virtual")
@CrossOrigin(origins = "*")
public class GaragemVirtualController {

    private final GaragemVirtualService service;

    public GaragemVirtualController(GaragemVirtualService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GaragemVirtual> criar(@RequestBody GaragemVirtual registro) {
        return ResponseEntity.ok(service.registrar(registro));
    }

    @GetMapping("/veiculo/{veiculoId}")
    public ResponseEntity<List<GaragemVirtual>> listarPorVeiculo(@PathVariable UUID veiculoId) {
        return ResponseEntity.ok(service.listarPorVeiculo(veiculoId));
    }
}