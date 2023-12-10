package com.lec.spring.controller;

import com.lec.spring.domain.ChatRoom;
import com.lec.spring.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private final ChatService chatService;

    @PostMapping("/")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping("/")
    @ResponseBody
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
    @RequestMapping("chatTest")
    public String chatListView(Model model){

        for(ChatRoom room : chatService.findAllRoom())
        {
            System.out.println(room.getRoom_id() + " " + room.getSubject());
            System.out.println(room);
        }
        model.addAttribute("chatName",1);

        return "chat/chatTest";
    }
}


