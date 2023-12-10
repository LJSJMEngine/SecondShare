package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import com.lec.spring.domain.Like;

import java.util.List;

public interface PostRepository {

    int save(Post post);

    Post findByPostId(Long id);

    int incViewCnt(Long id);

    List<Post> findAll();

    int update(Post post);

    List<Post> searchByTitle(@Param("keyword") String keyword);

    List<Post> searchByCategory(@Param("keyword") String keyword);
}
