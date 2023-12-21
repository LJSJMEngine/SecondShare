package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.ChatMessage;
import com.lec.spring.domain.ChatRoom;
import com.lec.spring.domain.User;
import com.lec.spring.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
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
    public String RoomCreate(@Valid ChatRoom cRoom, Model model, Authentication authentication) {
        System.out.println("PostData : " + cRoom);
        System.out.println("PostData : " + authentication);
        PrincipalDetails pDetails = (PrincipalDetails)authentication.getPrincipal();
        User currentUser = pDetails.getUser();
        User otherUser;
        int myId = Math.toIntExact(currentUser.getId());

        if(myId == chatService.getUser(cRoom.getPost_id()).getId()) { //지금 접속하고 있는 사람이 판매자인가?
            // 현재 접속자 : 판매자
            // 채팅 상대방 : 구매자

            if(cRoom.getBuyer_id() == myId) { // 내가 내 물건을 살 순 없다!
                return "redirect:/chat/roomDebug/";
            }
            otherUser = chatService.getUser(cRoom.getBuyer_id());

        }
        else {
            // 현재 접속자 : 구매자
            // 채팅 상대방 : 판매자
            otherUser = chatService.getUser(Math.toIntExact(chatService.getPostData(cRoom.getPost_id()).getUser_id()));
        }
        ChatRoom newRoom = chatService.findRoomByPostAndBuyer(cRoom.getPost_id(),cRoom.getBuyer_id());
        if(newRoom == null)
        {
            cRoom = chatService.createRoom(cRoom);
        }
        else
            cRoom = newRoom;


        System.out.println("DBInsert : " + cRoom);
        model.addAttribute("RoomData",cRoom); // 채팅방 정보
        model.addAttribute("PostData",chatService.getPostData(cRoom.getPost_id())); // 게시물 정보
        model.addAttribute("MessageList",chatService.findMessageFromRoomId(cRoom.getRoom_id())); // 이전 메세지 정보  *필요 없어보임



        model.addAttribute("currentUser",currentUser); // 현재 유저에 대한 정보
        model.addAttribute("otherUser",otherUser); // 상대 유저에 대한 정보
        return "chat/room";
    }
}

// 디테일 페이지 진입점 알려줘야함.