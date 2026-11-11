package com.baratex.api.dto;

import com.baratex.api.model.Servico;
import java.math.BigDecimal;
import java.util.UUID;

public record ServicoResponseDTO(
        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer tempoEstimadoMinutos,
        UUID oficinaId
) {
    public static ServicoResponseDTO deEntidade(Servico servico) {
        return new ServicoResponseDTO(
                servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getPrecoBase(), // Chama getPrecoBase() em vez de getPreco()
                servico.getTempoEstimadoMinutos(),
                servico.getOficina() != null ? servico.getOficina().getId() : null
        );
    }
}