package com.baratex.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record AgendamentoRequestDTO(
        @NotNull(message = "O ID da oficina é obrigatório.")
        UUID oficinaId,

        @NotNull(message = "O ID do veículo é obrigatório.")
        UUID veiculoId,

        @NotNull(message = "A data e hora do agendamento são obrigatórias.")
        LocalDateTime dataHora,

        @NotEmpty(message = "O agendamento deve conter ao menos um serviço.")
        @Valid
        List<ItemAgendamentoRequestDTO> itens
) {}