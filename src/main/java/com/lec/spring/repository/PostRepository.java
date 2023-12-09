package com.lec.spring.repository;

import com.lec.spring.domain.Like;
import com.lec.spring.domain.Post;

import java.util.List;

public interface PostRepository {
    int save(Post post); // 새글

    Post findByPostId(Long id);

    int incViewCnt(Long id);

    List<Post> findAll();

    int modify(Post post);
}
