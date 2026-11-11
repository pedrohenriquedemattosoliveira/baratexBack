package com.baratex.api.dto;

import com.baratex.api.model.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(
        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String senha,

        String telefone,

        String cpf,

        @NotNull(message = "O tipo de usuário é obrigatório.")
        TipoUsuario tipoUsuario
) {}