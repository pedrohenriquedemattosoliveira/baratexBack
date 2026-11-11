package com.baratex.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record VeiculoRequestDTO(
        @NotBlank(message = "A placa é obrigatória.")
        String placa,

        @NotNull(message = "O ID do modelo é obrigatório.")
        UUID modeloId,

        @NotNull(message = "O ano é obrigatório.")
        Integer ano,

        String cor
) {}