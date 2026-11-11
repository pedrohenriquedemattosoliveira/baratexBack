package com.baratex.api.controller;

import com.baratex.api.dto.MotoristaRequestDTO;
import com.baratex.api.dto.MotoristaResponseDTO;
import com.baratex.api.model.Motorista;
import com.baratex.api.model.TipoUsuario;
import com.baratex.api.model.Usuario;
import com.baratex.api.service.MotoristaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/motoristas")
@CrossOrigin(origins = "*")
public class MotoristaController {

    private final MotoristaService service;

    public MotoristaController(MotoristaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MotoristaResponseDTO> criar(@RequestBody @Valid MotoristaRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.usuario().nome());
        usuario.setEmail(dto.usuario().email());
        usuario.setSenhaHash(dto.usuario().senhaHash());
        usuario.setTelefone(dto.usuario().telefone());
        usuario.setTipoUsuario(TipoUsuario.MOTORISTA);

        Motorista motorista = new Motorista();
        motorista.setCpf(dto.cpf());
        motorista.setUsuario(usuario);

        Motorista salvo = service.salvar(motorista);
        return ResponseEntity.status(HttpStatus.CREATED).body(MotoristaResponseDTO.deEntidade(salvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoristaResponseDTO> buscarPorId(@PathVariable UUID id) {
        Motorista motorista = service.buscarPorId(id);
        return ResponseEntity.ok(MotoristaResponseDTO.deEntidade(motorista));
    }
}