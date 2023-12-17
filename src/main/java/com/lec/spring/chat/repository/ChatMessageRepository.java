package com.lec.spring.chat.repository;

import com.lec.spring.domain.ChatMessage;

import java.util.List;

public interface ChatMessageRepository {

    List<ChatMessage> findAllMessage();

    int createMessage(ChatMessage message);

    List<ChatMessage> findMessageFromRoomId(int room_id);

}
