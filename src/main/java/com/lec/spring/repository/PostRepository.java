package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostRepository {

    int save(Post post);

    Post findById(Long id);

    List<Post> findAll();

    int update(Post post);

}

