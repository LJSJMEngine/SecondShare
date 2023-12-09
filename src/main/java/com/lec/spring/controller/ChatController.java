package com.lec.spring.controller;

import com.lec.spring.domain.ChatRoom;
import com.lec.spring.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }

    @RequestMapping("/chatMake")
    public void ChatRoom (Model model)
    {
        System.out.println("chatMake");
        Random r = new Random();
        String chatName = chatService.createRoom("newChatName" + r.ints(25).toString()).getName();

        model.addAttribute("chatName",chatName);


    }

}


