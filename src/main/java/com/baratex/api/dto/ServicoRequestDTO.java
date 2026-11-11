package com.baratex.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record ServicoRequestDTO(
        @NotBlank String nome,
        String descricao,
        @NotNull BigDecimal preco,
        Integer tempoEstimadoMinutos,
        @NotNull UUID oficinaId
) {}