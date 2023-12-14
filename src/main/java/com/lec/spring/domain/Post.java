package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    private Long post_id;
    private Long user_id;
    private String subject;
    private String contents;
    private Integer price;
    private Integer viewCnt;
    private Integer status;
    private LocalDateTime regDate;

    // 마이페이지 - 내 판매물품
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MyPosts {
        private Long post_id;
        private Long user_id;
        private String subject;
        private Integer status;
        private LocalDateTime regDate;
    }


}
