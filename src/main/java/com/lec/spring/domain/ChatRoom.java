package com.lec.spring.domain;

import com.lec.spring.service.ChatService;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {

    //post_id, seller_id, buyer_id 세 값이 없으면 채팅방 생성이 불가능.
    private int room_id;
    private int post_id;
    private int seller_id;
    private int buyer_id;


    //추가하는 데이터 목록
    private Date createDate; // 생성 시간
    private Date lastUpdateDate; // 최종 수정 시간
    private String subject; // 접속 ID
    private int roomState; // 방 상태
    /*
     * 0 : 거래 중
     * 1 : 거래 완료
     * 2 : etc...
     */

    public enum CREATETYPE{
        POSTTRADE,
        NONE
    }
    ///desc?
    public void instanciate(CREATETYPE initType) {


        String roomName = "";
        // 이 후 유지보수에서 별도의 채팅방이 필요 할 경우 필요한 분기타입
        switch (initType)
        {
            case POSTTRADE,NONE -> {
                roomName = post_id + "#" + buyer_id + "#" + seller_id;

            }
        }
        // DB에서 생성된 값 이외의 데이터 초기화
        subject = roomName;
        createDate = Date.valueOf(LocalDate.now());
        roomState = 0;
    }
}
