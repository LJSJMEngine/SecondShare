package com.lec.spring.repository;

import com.lec.spring.domain.ChatRoom;
import jakarta.annotation.PostConstruct;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ChatRepository {
    ChatRoom findRoomById(String room_id);
    List<ChatRoom> findAllRoom();
    int createChatRoom(ChatRoom chatRoom);

    ChatRoom findRoomByUserAndPostId(ChatRoom chatRoom);
}
