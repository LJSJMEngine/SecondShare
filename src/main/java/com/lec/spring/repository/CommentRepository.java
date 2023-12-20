package com.lec.spring.repository;

import com.lec.spring.domain.Comment;

import java.util.List;

public interface CommentRepository {

    // 특정 글 댓글 목록
    List<Comment> findByPost(Long post_id);

    // 댓글 작성
    int save(Comment comment);

    // 특정 댓글 삭제
    int deleteById(Long id);
}
