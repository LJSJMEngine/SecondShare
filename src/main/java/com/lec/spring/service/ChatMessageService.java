package com.lec.spring.service;

import com.lec.spring.domain.ChatMessage;
import com.lec.spring.repository.ChatMessageRepository;

import java.util.List;

public interface ChatMessageService {
    List<ChatMessage> findAllMessage();

    List<ChatMessage> findMessageFromRoomId(int room_id);

    ChatMessage createMessage(ChatMessage message);


}
