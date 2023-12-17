package com.lec.spring.chat.service;

import com.lec.spring.domain.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    List<ChatMessage> findAllMessage();

    List<ChatMessage> findMessageFromRoomId(int room_id);

    ChatMessage createMessage(ChatMessage message);


}
