package com.lec.spring.repository;

import com.lec.spring.domain.*;
import jakarta.annotation.PostConstruct;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ChatRepository {
    List<ChatRoom> findAllRoom();
    ChatRoom findRoomById(int room_id);

    int createChatRoom(ChatRoom chatRoom);

    ChatRoom findRoomByPostAndBuyer(int Post_id,int Buyer_id);
    void updateRoomState(int post_id, int roomState);

    void updateRoomLastDate(@Param("room_id") int room_id,@Param("lastUpdateDate") Timestamp lastUpdateDate);

    Post getPostData(int post_id);

    List<ChatMessage> findMessageFromRoomId(int room_id);

    User getUser(int user_id);

    Attachment getSampleImg(int id);


}
