package com.baratex.api.controller;

import com.baratex.api.dto.VeiculoRequestDTO;
import com.baratex.api.dto.VeiculoResponseDTO;
import com.baratex.api.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> cadastrarVeiculo(@RequestBody @Valid VeiculoRequestDTO request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = auth.getName(); // Recupera o e-mail injetado pelo JwtAuthenticationFilter

        VeiculoResponseDTO novoVeiculo = veiculoService.cadastrarVeiculo(request, emailUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listarMeusVeiculos() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = auth.getName();

        List<VeiculoResponseDTO> veiculos = veiculoService.listarMeusVeiculos(emailUsuario);
        return ResponseEntity.ok(veiculos);
    }
}