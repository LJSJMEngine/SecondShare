package com.lec.spring.service;

import com.lec.spring.domain.ChatMessage;
import com.lec.spring.repository.ChatMessageRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{

    private ChatMessageRepository messageRepo;

    @Autowired
    public ChatMessageServiceImpl(SqlSession sqlss){
        this.messageRepo = sqlss.getMapper(ChatMessageRepository.class);
        System.out.println("chatMessageService Init");
    }

    @Override
    public List<ChatMessage> findAllMessage() {
        return messageRepo.findAllMessage();
    }

    @Override
    public List<ChatMessage> findMessageFromRoomId(int room_id) {
        return messageRepo.findMessageFromRoomId(room_id);
    }
    @Override
    public ChatMessage createMessage(ChatMessage message) {


        message.setCreateDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        messageRepo.createMessage(message);

        System.out.println("[SECONDSHARE] ChatMessage : CreateSuccessMessage");
        return message;
    }
}
