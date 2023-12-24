package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private int id;
    private int user_id;
    private int post_id;
    private int reviewChk;
    private int reviewRate;
    private String content;

    // 마이페이지 - 내가 받은 리뷰
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MyReceivedReviews {
        private int id;
        private int user_id;
        private int post_id;
        private int reviewChk;
        private String content;
    }
}
