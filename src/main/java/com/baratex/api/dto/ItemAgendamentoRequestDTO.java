package com.baratex.api.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemAgendamentoRequestDTO(
        @NotNull(message = "O ID do serviço é obrigatório.")
        UUID servicoId,

        BigDecimal precoPraticado
) {}