package com.lec.spring.domain;

import com.lec.spring.service.ChatService;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class ChatRoom {
    private String roomId;
    private String name;

    public static ChatRoom createRoom(String name){
        ChatRoom newRoom = new ChatRoom();
        newRoom.name = name;
        newRoom.roomId = UUID.randomUUID().toString();
        return newRoom;

    }
}
