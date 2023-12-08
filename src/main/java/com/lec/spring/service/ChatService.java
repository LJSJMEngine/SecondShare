package com.lec.spring.service;

import com.lec.spring.domain.ChatRoom;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public interface ChatService {
    void init();

    List<ChatRoom> findAllRoom();

    ChatRoom findRoomById(String roomId);

    ChatRoom createRoom(String name);

    <T> void sendMessage(WebSocketSession session, T message);
}