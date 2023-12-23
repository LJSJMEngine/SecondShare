package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {

    private Long id;
    private Long post_id;
    private String sourcename;
    private String filename;
    private boolean isImage;        // 이미지 파일이면 true, 아니면 false
    private boolean isSampleImg;    // 대표 이미지면 true, 아니면 false

    public void setIsSampleImg(boolean isSampleImg) {
        this.isSampleImg = isSampleImg;
    }
}