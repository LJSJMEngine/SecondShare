package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.lec.spring.domain.Heart;

import java.util.List;

public interface    PostRepository {

    int save(Post post);

    Post findByPostId(Long id);

    int incViewCnt(Long id);

    List<Post> findAll();

    int update(Post post);

    int delete(Post post);

    List<Post> search(@Param("keyword") String keyword);

    // 페이징 from 부터 rows 개 만큼
    List<Post> selectFromRow(int from, int rows);

    //전체 글 개수
    int countAll();



}
