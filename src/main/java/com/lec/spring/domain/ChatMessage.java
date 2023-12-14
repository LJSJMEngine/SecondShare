package com.lec.spring.domain;

import lombok.*;

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
    private MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}
