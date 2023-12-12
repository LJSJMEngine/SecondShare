package com.lec.spring.controller;

import com.lec.spring.domain.ChatMessage;
import com.lec.spring.domain.ChatRoom;
import com.lec.spring.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
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
    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return null;
    }

    @GetMapping("/")
    @ResponseBody
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }

    @RequestMapping("chatTest")
    public String chatListView(Model model) {

        for (ChatRoom room : chatService.findAllRoom()) {
            System.out.println(room.getRoom_id() + " " + room.getSubject());
            System.out.println(room);
        }
        model.addAttribute("chatName", 1);

        return "chat/chatTest";
    }

    @RequestMapping("room")
    public void roomtest() {
    }

    @RequestMapping("roomdetail")
    public void roomdetail() {
    }

    @PostMapping("roomdetail")
    public void PostRoomDetail(@Valid ChatRoom roomData, Model model) {
        model.addAttribute("roomInfo", roomData);


    }

    @RequestMapping("roomDebug")
    public String RoomDebug() {
        return "chat/roomDebug";
    }
    @PostMapping("roomDebug")
    public String RoomCreate(@Valid ChatRoom cRoom, Model model) {
        System.out.println("PostData : " + cRoom);

        cRoom = chatService.createRoom(cRoom);
        System.out.println("DBInsert : " + cRoom);
        model.addAttribute("RoomData",cRoom);
        return "chat/roomDebug";
    }


    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {


        if (ChatMessage.MessageType.JOIN.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");


        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

}

// 디테일 페이지 진입점 알려줘야함.