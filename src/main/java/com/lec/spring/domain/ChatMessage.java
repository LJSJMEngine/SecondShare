package com.lec.spring.domain;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        JOIN, TALK
    }

    //https://supawer0728.github.io/2018/03/30/spring-websocket/
    private MessageType type; // 메시지 타입, DB에 저장하지 않습니다.


    private int chat_id;
    private int room_id; // 방번호
    private int sender_id; // 메시지 보낸사람
    private String content; // 메시지
    private Timestamp createDate; // 생성 시간
    private boolean checkedContent; // 해당 메세지 확인 여부
}
