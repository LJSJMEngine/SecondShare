package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long post_id;// 글 번호
    private Long user_id;
    private String subject;
    private String contents;
    private int price;
    private Long viewCnt;
    private LocalDateTime regDate;
    private int status;
    private int goodCnt;




    private Category category; // 카테고리

    private User user; // 글 작성자


}
