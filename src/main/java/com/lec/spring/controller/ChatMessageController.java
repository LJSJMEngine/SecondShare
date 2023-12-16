package com.lec.spring.controller;

import com.lec.spring.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatMessageController {
    private final SimpMessageSendingOperations messagingTemplate;
    @MessageMapping("/message")
    public ChatMessage chatMessage(ChatMessage message) {
        //db에 저장+
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(),message);
        System.out.println("My : MessageInServer");
        System.out.println(message.getRoomId());
        return  message;
    }

}
