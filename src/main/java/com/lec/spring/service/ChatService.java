package com.lec.spring.service;

import com.lec.spring.domain.ChatMessage;
import com.lec.spring.domain.ChatRoom;
import org.springframework.web.socket.WebSocketSession;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public interface ChatService {


    List<ChatRoom> findAllRoom();

    ChatRoom findRoomById(int roomId);
    List<ChatMessage> findMessageFromRoomId(int room_id);

    ChatRoom createRoom(ChatRoom chatRoom);

    ChatRoom findRoomByPostAndBuyer(int Post_id,int Buyer_id);

    void updateRoomState(int post_id, int roomState);

    void updateRoomLastDate(int room_id, Timestamp lastUpdateDate);

}