package com.lec.spring.repository;

import com.lec.spring.domain.ChatMessage;
import com.lec.spring.domain.ChatRoom;
import jakarta.annotation.PostConstruct;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ChatRepository {
    List<ChatRoom> findAllRoom();
    ChatRoom findRoomById(String room_id);

    int createChatRoom(ChatRoom chatRoom);

    ChatRoom findRoomByPostAndBuyer(int Post_id,int Buyer_id);
    void updateRoomState(int post_id, int roomState);
}
