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
public class Heart {
    private int id;        // 좋아요 id
    private int user_id;   // 유저 id
    private int post_id;   // 포스트 id
    private boolean is_active;      // 좋아요 클릭 시 true (default false)
    private LocalDateTime created_at;   // 좋아요 누른 시간 (처음) default now()
    private LocalDateTime updated_at;   // (있다면) 좋아요 취소하고 다시 누른 시간

    private User user;
    private Post post;
}
