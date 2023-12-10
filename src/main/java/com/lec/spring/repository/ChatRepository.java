package com.lec.spring.repository;

import com.lec.spring.domain.ChatRoom;
import jakarta.annotation.PostConstruct;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ChatRepository {

    public ChatRoom findRoomById(String room_id);

    public List<ChatRoom> findAllRoom();

    public ChatRoom createChatRoom(String name);
}
