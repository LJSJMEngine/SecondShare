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

    public Heart(int id, int user_id, int post_id, boolean is_active, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.is_active = is_active;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Heart(boolean is_active, String message, int heartCount) {
        this.is_active = is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public boolean isIs_active() {
        return is_active;
    }
}