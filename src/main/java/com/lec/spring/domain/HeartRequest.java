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
public class HeartRequest {
    private int user_id;
    private int post_id;

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    private boolean is_active;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Heart toHeart() {
        return Heart.builder()
                .user_id(this.user_id)
                .post_id(this.post_id)
                .is_active(this.is_active)
                .created_at(this.created_at)
                .updated_at(this.updated_at)
                .build();
    }
}