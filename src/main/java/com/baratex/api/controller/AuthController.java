package com.baratex.api.controller;

import com.baratex.api.dto.LoginRequestDTO;
import com.baratex.api.dto.LoginResponseDTO;
import com.baratex.api.dto.RegisterRequestDTO;
import com.baratex.api.model.Motorista;
import com.baratex.api.model.TipoUsuario;
import com.baratex.api.model.Usuario;
import com.baratex.api.repository.MotoristaRepository;
import com.baratex.api.repository.UsuarioRepository;
import com.baratex.api.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final MotoristaRepository motoristaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthController(UsuarioRepository usuarioRepository,
                          MotoristaRepository motoristaRepository,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider tokenProvider) {
        this.usuarioRepository = usuarioRepository;
        this.motoristaRepository = motoristaRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas."));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenhaHash())) {
            throw new RuntimeException("Credenciais inválidas.");
        }

        String token = tokenProvider.gerarToken(usuario);

        LoginResponseDTO response = new LoginResponseDTO(
                token,
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario()
        );

        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(request.nome());
        novoUsuario.setEmail(request.email());
        novoUsuario.setSenhaHash(passwordEncoder.encode(request.senha()));
        novoUsuario.setTelefone(request.telefone());
        novoUsuario.setTipoUsuario(request.tipoUsuario());

        Usuario usuarioSalvo = usuarioRepository.saveAndFlush(novoUsuario);

        if (request.tipoUsuario() == TipoUsuario.MOTORISTA) {
            if (request.cpf() == null || request.cpf().isBlank()) {
                return ResponseEntity.badRequest().body("O CPF é obrigatório para cadastrar um motorista.");
            }

            Motorista motorista = new Motorista();
            motorista.setUsuario(usuarioSalvo);
            motorista.setCpf(request.cpf().replaceAll("\\D", ""));
            motoristaRepository.save(motorista);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
}