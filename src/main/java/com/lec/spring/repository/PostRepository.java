package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostRepository {

    int save(Post post);

    Post findByPostId(Long id);

    int incViewCnt(Long id);

    List<Post> findAll();

    int update(Post post);

    int delete(Post post);

    List<Post> search(@Param("keyword") String keyword);

    List<Post> searchByCategory(@Param("keyword") String keyword);

    int countAll();
}
