package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    private Long user_id;
    private Long post_id;
    private String subject;

    private String contents;

    private Long price;

    private Long viewCnt;

    private Long status;

    private LocalDateTime regDate;
}
