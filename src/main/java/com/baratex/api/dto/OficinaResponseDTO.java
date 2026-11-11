package com.baratex.api.dto;

import com.baratex.api.model.Oficina;
import java.util.UUID;

public record OficinaResponseDTO(
        UUID id,
        String razaoSocial,
        String nomeFantasia,
        String cnpj,
        String nomeUsuario,
        String email,
        String telefone,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        Double latitude,
        Double longitude
) {
    public static OficinaResponseDTO deEntidade(Oficina oficina) {
        return new OficinaResponseDTO(
                oficina.getId(),
                oficina.getRazaoSocial(),
                oficina.getNomeFantasia(),
                oficina.getCnpj(),
                oficina.getUsuario() != null ? oficina.getUsuario().getNome() : null,
                oficina.getUsuario() != null ? oficina.getUsuario().getEmail() : null,
                oficina.getUsuario() != null ? oficina.getUsuario().getTelefone() : null,
                oficina.getEndereco() != null ? oficina.getEndereco().getLogradouro() : null,
                oficina.getEndereco() != null ? oficina.getEndereco().getNumero() : null,
                oficina.getEndereco() != null ? oficina.getEndereco().getBairro() : null,
                oficina.getEndereco() != null ? oficina.getEndereco().getCidade() : null,
                oficina.getEndereco() != null ? oficina.getEndereco().getEstado() : null,
                oficina.getEndereco() != null ? oficina.getEndereco().getLatitude() : null,
                oficina.getEndereco() != null ? oficina.getEndereco().getLongitude() : null
        );
    }
}