package com.baratex.api.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErroResposta(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        String mensagem,
        String path,
        Map<String, String> errosValidacao
) {
    public ErroResposta(Integer status, String erro, String mensagem, String path) {
        this(LocalDateTime.now(), status, erro, mensagem, path, null);
    }

    public ErroResposta(Integer status, String erro, String mensagem, String path, Map<String, String> errosValidacao) {
        this(LocalDateTime.now(), status, erro, mensagem, path, errosValidacao);
    }
}