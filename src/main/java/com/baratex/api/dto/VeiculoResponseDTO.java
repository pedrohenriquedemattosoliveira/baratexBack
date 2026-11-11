package com.baratex.api.dto;

import java.util.UUID;

public record VeiculoResponseDTO(
        UUID id,
        String placa,
        String marca,
        String modelo,
        Integer ano,
        String cor
) {}