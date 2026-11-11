package com.baratex.api.dto;

import com.baratex.api.model.TipoUsuario;
import java.util.UUID;

public record LoginResponseDTO(
        String token,
        String tipo,
        UUID usuarioId,
        String nome,
        String email,
        TipoUsuario tipoUsuario
) {
    public LoginResponseDTO(String token, UUID usuarioId, String nome, String email, TipoUsuario tipoUsuario) {
        this(token, "Bearer", usuarioId, nome, email, tipoUsuario);
    }
}