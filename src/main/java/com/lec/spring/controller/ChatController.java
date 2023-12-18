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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private final ChatService chatService;

    @MessageMapping("/roomUpdateDate")
    public void lastUpdateDate(ChatMessage message) {
        // lastUpdateDate 업데이트해야함.
        System.out.println("MESSAGE SEND : lastUpdateDate");

        Timestamp currentDate = java.sql.Timestamp.valueOf(LocalDateTime.now());
        chatService.updateRoomLastDate(message.getRoom_id(), currentDate);


    }

    @MessageMapping("/roomUpdateState")
    public void roomUpdateState(ChatMessage message){

    }
    @RequestMapping("roomDebug")
    public String RoomDebug() {
        return "chat/roomDebug";
    }
    @PostMapping("room")
    public String RoomCreate(@Valid ChatRoom cRoom, Model model) {
        System.out.println("PostData : " + cRoom);

        ChatRoom newRoom = chatService.findRoomByPostAndBuyer(cRoom.getPost_id(),cRoom.getBuyer_id());
        if(newRoom == null)
        {
            cRoom = chatService.createRoom(cRoom);
        }
        else
            cRoom = newRoom;
        System.out.println("DBInsert : " + cRoom);
        model.addAttribute("RoomData",cRoom);
        return "chat/room";
    }
}

// 디테일 페이지 진입점 알려줘야함.