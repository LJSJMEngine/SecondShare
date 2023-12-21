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
public class Notice {
    int id;
    int user_id;
    int status;
    String status_name;
    String content;
    boolean readChk;
    // private int id;
    // private int user_id;
    // private int comment_id;
    // private int post_id;
    // private int review_id;
    // private int type;
    // private int status;
    // private String contents;
    // private boolean readChk;
    // private LocalDateTime regDate;

}
