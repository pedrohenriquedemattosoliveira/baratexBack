package com.baratex.api.controller;

import com.baratex.api.dto.OficinaRequestDTO;
import com.baratex.api.dto.OficinaResponseDTO;
import com.baratex.api.model.Endereco;
import com.baratex.api.model.Oficina;
import com.baratex.api.model.TipoUsuario;
import com.baratex.api.model.Usuario;
import com.baratex.api.service.OficinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/oficinas")
@CrossOrigin(origins = "*")
public class OficinaController {

    private final OficinaService service;

    public OficinaController(OficinaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OficinaResponseDTO>> listarTodas() {
        List<Oficina> oficinas = service.listarTodas();
        List<OficinaResponseDTO> dtos = oficinas.stream().map(OficinaResponseDTO::deEntidade).toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<OficinaResponseDTO> criar(@RequestBody @Valid OficinaRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.usuario().nome());
        usuario.setEmail(dto.usuario().email());
        usuario.setSenhaHash(dto.usuario().senhaHash());
        usuario.setTelefone(dto.usuario().telefone());
        usuario.setTipoUsuario(TipoUsuario.OFICINA);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.endereco().logradouro());
        endereco.setNumero(dto.endereco().numero());
        endereco.setBairro(dto.endereco().bairro());
        endereco.setCidade(dto.endereco().cidade());
        endereco.setEstado(dto.endereco().estado());
        endereco.setCep(dto.endereco().cep());
        endereco.setLatitude(dto.endereco().latitude());
        endereco.setLongitude(dto.endereco().longitude());

        Oficina oficina = new Oficina();
        oficina.setRazaoSocial(dto.razaoSocial());
        oficina.setNomeFantasia(dto.nomeFantasia());
        oficina.setCnpj(dto.cnpj());
        oficina.setUsuario(usuario);
        oficina.setEndereco(endereco);

        Oficina salva = service.salvar(oficina);
        return ResponseEntity.status(HttpStatus.CREATED).body(OficinaResponseDTO.deEntidade(salva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OficinaResponseDTO> buscarPorId(@PathVariable UUID id) {
        Oficina oficina = service.buscarPorId(id);
        return ResponseEntity.ok(OficinaResponseDTO.deEntidade(oficina));
    }

    @GetMapping("/proximas")
    public ResponseEntity<List<OficinaResponseDTO>> buscarProximos(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam(defaultValue = "10") Double raioKm) {
        List<Oficina> oficinas = service.buscarProximos(latitude, longitude, raioKm);
        List<OficinaResponseDTO> dtos = oficinas.stream().map(OficinaResponseDTO::deEntidade).toList();
        return ResponseEntity.ok(dtos);
    }
}