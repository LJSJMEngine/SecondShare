package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Heart {
    private Long id;        // 좋아요 id
    private Long user_id;   // 유저 id
    private Long post_id;   // 포스트 id
    private int heart;      // 좋아요
}
