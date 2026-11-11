package com.baratex.api.service;

import com.baratex.api.model.Motorista;
import com.baratex.api.repository.MotoristaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MotoristaService {

    private final MotoristaRepository repository;
    private final PasswordEncoder passwordEncoder;

    public MotoristaService(MotoristaRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Motorista salvar(Motorista motorista) {
        String senhaPura = motorista.getUsuario().getSenhaHash();
        String senhaCriptografada = passwordEncoder.encode(senhaPura);
        motorista.getUsuario().setSenhaHash(senhaCriptografada);

        return repository.save(motorista);
    }

    public Motorista buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Motorista não encontrado com o ID: " + id));
    }
}