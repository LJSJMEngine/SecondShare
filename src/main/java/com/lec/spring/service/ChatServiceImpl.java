package com.lec.spring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.domain.*;
import com.lec.spring.repository.AttachmentRepository;
import com.lec.spring.repository.ChatRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatRepository chatRepo;
    private AttachmentRepository attRepo;

    @Autowired
    public ChatServiceImpl(SqlSession sqlss) {
        this.chatRepo = sqlss.getMapper(ChatRepository.class);
        this.attRepo = sqlss.getMapper(AttachmentRepository.class);
        System.out.println("chatService Init");

    }

    public List<ChatRoom> findAllRoom() {
        return chatRepo.findAllRoom();
    }

    public ChatRoom findRoomById(int roomId) {
        return chatRepo.findRoomById(roomId);
    }

    @Override
    public List<ChatMessage> findMessageFromRoomId(int room_id) {
        return chatRepo.findMessageFromRoomId(room_id);
    }

    public ChatRoom createRoom(ChatRoom cRoom) {
        // 매핑 된 DB에서 채팅방 생성

        cRoom.instanciate(ChatRoom.CREATETYPE.POSTTRADE);
        Post p = chatRepo.getPostData(cRoom.getPost_id());
        cRoom.setSubject(p.getSubject() + " 게시글의 채팅방");
        chatRepo.createChatRoom(cRoom);

        System.out.println("[SECONDSHARE] Chat : CreateSuccessRoom");
        return cRoom;
    }
    @Override
    public ChatRoom findRoomByPostAndBuyer(int Post_id,int Buyer_id)
    {
        return chatRepo.findRoomByPostAndBuyer(Post_id,Buyer_id);
    }


    @Override
    public void updateRoomState(int post_id, int roomState) {
        //방 상태 변경 판매 중 <-> 판매 완료
        chatRepo.updateRoomState(post_id, roomState);

        System.out.println("[SECONDSHARE] Chat : change RoomState, Post_id : " + post_id + ", STATE : " + roomState);

    }

    @Override
    public void updateRoomLastDate(int room_id, Timestamp lastUpdateDate)
    {
        chatRepo.updateRoomLastDate(room_id,lastUpdateDate);
    }

    @Override
    public Post getPostData(int post_id) {

        List<Attachment> fileList = attRepo.findByPost((long) post_id);
        Post result =chatRepo.getPostData(post_id);
        result.setFileList(fileList);

        return result;

    }

    @Override
    public User getUser(int user_id) {
        return chatRepo.getUser(user_id);
    }

    @Override
    public Attachment getSampleImg(int id) {
        return chatRepo.getSampleImg(id);
    }

}
