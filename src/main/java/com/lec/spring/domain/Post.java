package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    private Long post_id;// 글 번호
    private Long user_id;
    private Long category_id;
    private String subject;
    private String contents;
    private Long price;
    private int viewCnt;
    private int status;
    private LocalDateTime regDate;
    private int sampleImg;  // default 0 , 첨부파일 자체가 없으면 0, 첨부파일 (이미지, 텍스트 등 모든 형태)가 있으면 1
    private List<Heart> hearts; // 해당 판매글에 대한 좋아요 목록

    private User user;
    private Category category;
    private Attachment attachment;

    // 마이페이지 - 내 판매물품
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MyPosts {
        private Long post_id;
        private Long user_id;
        private String subject;
        private int status;
        private LocalDateTime regDate;
        private String spImg;
    }

    // 첨부파일
    @ToString.Exclude
    @Builder.Default
    private List<Attachment> fileList = new ArrayList<>();



}