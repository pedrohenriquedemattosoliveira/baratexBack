package com.baratex.api.dto;

import com.baratex.api.model.Motorista;
import java.util.UUID;

public record MotoristaResponseDTO(
        UUID id,
        String cpf,
        String nome,
        String email,
        String telefone
) {
    public static MotoristaResponseDTO deEntidade(Motorista motorista) {
        return new MotoristaResponseDTO(
                motorista.getId(),
                motorista.getCpf(),
                motorista.getUsuario().getNome(),
                motorista.getUsuario().getEmail(),
                motorista.getUsuario().getTelefone()
        );
    }
}