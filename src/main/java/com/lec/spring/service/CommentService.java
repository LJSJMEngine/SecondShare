package com.lec.spring.service;

import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;

public interface CommentService {

    // 특정 글의 댓글 목록
    QryCommentList list(Long id);

    // 특정 글에 특정 사용자가 댓글 작성
    QryResult write(Long post_id, Long user_id, String content);

    // 특정 댓글 삭제
    QryResult delete(Long id);
}
