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
        // 매핑 된 DB에서 채팅방 생성
        ChatRoom newRoom = chatRepo.createChatRoom(post_id,buyer_id,seller_id);
        newRoom.instanciate(ChatRoom.CREATETYPE.POSTTRADE);
        return newRoom;
    }
}
