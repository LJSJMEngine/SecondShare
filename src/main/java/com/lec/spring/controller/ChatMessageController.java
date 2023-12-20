package com.lec.spring.controller;

import com.lec.spring.domain.ChatMessage;
import com.lec.spring.service.ChatMessageService;
import com.lec.spring.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatMessageController {
    @Autowired
    private final ChatMessageService messageService;

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/message")
    public ChatMessage chatMessage(ChatMessage message) {
        messageService.createMessage(message);
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoom_id(),message);

        //database 저장
        return  message;
    }
    @MessageMapping("/init")
    public void chatInit(ChatMessage message) throws InterruptedException {

        List<ChatMessage> messageList = messageService.findMessageFromRoomId(message.getRoom_id());
        for (ChatMessage msg : messageList) {
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoom_id(),msg);
            Thread.sleep(10);

        }
    }

}