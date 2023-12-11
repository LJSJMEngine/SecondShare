package com.lec.spring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.domain.ChatRoom;
import com.lec.spring.repository.ChatRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatRepository chatRepo;

    @Autowired
    public ChatServiceImpl(SqlSession sqlss) {
        this.chatRepo = sqlss.getMapper(ChatRepository.class);
        System.out.println("chatService Init");

    }

    public List<ChatRoom> findAllRoom() {
        return chatRepo.findAllRoom();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRepo.findRoomById(roomId);
    }

    public ChatRoom createRoom(int post_id, int buyer_id, int seller_id) {
        //채팅방 이름은 게시글 id + 구매자 id + 판매자 id값의 String
        //각 해시 값의 사이는 #로 구분

        String chatName = post_id + "#" + buyer_id + "#" + seller_id;


        return chatRepo.createChatRoom(name);
    }
}
