package com.baratex.api.controller;

import com.baratex.api.model.MensagemChat;
import com.baratex.api.service.MensagemChatService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Hidden
@Controller
public class ChatWebSocketController {

    private final MensagemChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(MensagemChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat/{agendamentoId}")
    public void processarMensagem(@DestinationVariable UUID agendamentoId, @Payload MensagemChat mensagem) {
        MensagemChat mensagemSalva = chatService.enviarMensagem(mensagem);
        messagingTemplate.convertAndSend("/topic/agendamento/" + agendamentoId, mensagemSalva);
    }
}