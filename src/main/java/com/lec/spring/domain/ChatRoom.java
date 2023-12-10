package com.lec.spring.domain;

import com.lec.spring.service.ChatService;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {

    private int room_id;
    private int post_id;
    private int seller_id;
    private int buyer_id;
    private Date createDate;
    private Date lastUpdateDate;
    private String subject;
    private int roomState;
    public static ChatRoom createRoom(){
        ChatRoom newRoom = new ChatRoom();
        newRoom.subject = UUID.randomUUID().toString();
        return newRoom;

    }
}
