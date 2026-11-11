package com.baratex.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OficinaRequestDTO(
        @NotBlank String razaoSocial,
        @NotBlank String nomeFantasia,
        @NotBlank String cnpj,
        @NotNull @Valid UsuarioDTO usuario,
        @NotNull @Valid EnderecoDTO endereco
) {
        public record UsuarioDTO(
                @NotBlank String nome,
                @NotBlank String email,
                @NotBlank String senhaHash,
                String telefone
        ) {}

        public record EnderecoDTO(
                String logradouro,
                String numero,
                String bairro,
                String cidade,
                String estado,
                String cep,
                Double latitude,
                Double longitude
        ) {}
}