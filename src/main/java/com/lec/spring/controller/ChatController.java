package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.*;
import com.lec.spring.service.ChatService;
import com.lec.spring.service.PostService;
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
    @PostMapping("roomConnect")
    public String roomConnect(
            @RequestParam("postId") int post_id,
            @RequestParam("buyerId") int buyer_id,
            Model model,
            Authentication authentication)
    {
        PrincipalDetails pDetails = (PrincipalDetails)authentication.getPrincipal();
        User currentUser = pDetails.getUser();
        Post postData = chatService.getPostData(post_id);
        int myId = Math.toIntExact(currentUser.getId());

        User seller = chatService.getUser(Math.toIntExact(postData.getUser_id()));
        User buyer = chatService.getUser(buyer_id);

        String sellerFlag;

        if(seller == null || buyer == null) {
            //notice ( 채팅방 에러 )
            System.out.println("[CHATCONTROLLER, ROOMCONNECT] : 구매자 혹은 판매자 ID가 없습니다.");
            return "redirect:/main";
        }
        else if(seller.getId() != myId || buyer.getId() != myId){
            System.out.println("[CHATCONTROLLER, ROOMCONNECT] : 거래에 관련 없는 유저가 접속해있습니다.");
            return "redirect:/main";
        }

        sellerFlag = seller.getId() == myId ? "SELLER" : "BUYER";

        model.addAttribute("postId",post_id);
        model.addAttribute("buyerId",buyer_id);
        model.addAttribute("sellerId",seller.getId());
        model.addAttribute("sellerFlag",sellerFlag);
        return "chat/roomConnect";
    }
    @PostMapping("room")
    public String RoomCreate(@Valid ChatRoom cRoom, Model model, Authentication authentication) {
        System.out.println("PostData : " + cRoom);
        System.out.println("PostData : " + authentication);
        PrincipalDetails pDetails = (PrincipalDetails)authentication.getPrincipal();
        User currentUser = pDetails.getUser();
        User otherUser;
        User seller;
        Post postData = chatService.getPostData(cRoom.getPost_id());


        ChatRoom newRoom = chatService.findRoomByPostAndBuyer(cRoom.getPost_id(),cRoom.getBuyer_id());
        if(newRoom == null)
        {
            cRoom = chatService.createRoom(cRoom);
        }
        else
            cRoom = newRoom;



        System.out.println("DBInsert : " + cRoom);
        Attachment mainImg = chatService.getSampleImg(Math.toIntExact(postData.getPost_id()));
        model.addAttribute("MainImg", mainImg); // 이미지 정보
        model.addAttribute("RoomData",cRoom); // 채팅방 정보
        model.addAttribute("PostData",postData); // 게시물 정보
        model.addAttribute("MessageList",chatService.findMessageFromRoomId(cRoom.getRoom_id())); // 이전 메세지 정보  *필요 없어보임
        model.addAttribute("currentUser",currentUser); // 현재 유저에 대한 정보
        //model.addAttribute("otherUser",otherUser); // 상대 유저에 대한 정보
        return "chat/room";
    }
}

// 디테일 페이지 진입점 알려줘야함.