package com.lec.spring.chat.service;

import com.lec.spring.domain.ChatRoom;
import com.lec.spring.chat.repository.ChatRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatRepository chatRepo;

    @Autowired
    public ChatServiceImpl(SqlSession sqlss) {
        this.chatRepo = sqlss.getMapper(ChatRepository.class);
        System.out.println("chatService Init");

    }

    public List<ChatRoom> findAllRoom() {
        return chatRepo.findAllRoom();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRepo.findRoomById(roomId);
    }

    public ChatRoom createRoom(ChatRoom cRoom) {
        // 매핑 된 DB에서 채팅방 생성

        cRoom.instanciate(ChatRoom.CREATETYPE.POSTTRADE);
        chatRepo.createChatRoom(cRoom);

        System.out.println("[SECONDSHARE] Chat : CreateSuccessRoom");
        return cRoom;
    }

    @Override
    public ChatRoom findRoomByPostAndBuyer(int Post_id,int Buyer_id)
    {
        return chatRepo.findRoomByPostAndBuyer(Post_id,Buyer_id);
    }
}
