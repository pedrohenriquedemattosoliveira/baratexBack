package com.baratex.api.controller;

import com.baratex.api.model.MensagemChat;
import com.baratex.api.service.MensagemChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class MensagemChatController {

    private final MensagemChatService service;

    public MensagemChatController(MensagemChatService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MensagemChat> enviar(@RequestBody MensagemChat mensagem) {
        return ResponseEntity.ok(service.enviarMensagem(mensagem));
    }

    @GetMapping("/agendamento/{agendamentoId}")
    public ResponseEntity<List<MensagemChat>> listarPorAgendamento(@PathVariable UUID agendamentoId) {
        return ResponseEntity.ok(service.listarPorAgendamento(agendamentoId));
    }
}