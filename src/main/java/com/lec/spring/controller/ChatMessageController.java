package com.lec.spring.controller;

import com.lec.spring.domain.ChatMessage;
import com.lec.spring.service.ChatMessageService;
import com.lec.spring.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatMessageController {
    @Autowired
    private final ChatMessageService messageService;

    private final SimpMessageSendingOperations messagingTemplate;
    @MessageMapping("/message")
    public ChatMessage chatMessage(ChatMessage message) {
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoom_id(),message);

        messageService.createMessage(message);
        //database 저장

        return  message;
    }

}