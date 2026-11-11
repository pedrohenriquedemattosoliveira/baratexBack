package com.baratex.api.dto;

import java.util.UUID;

public record ModeloVeiculoDTO(
        UUID id,
        String nomeModelo,
        Integer anoInicio,
        Integer anoFim
) {}