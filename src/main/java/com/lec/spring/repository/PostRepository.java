package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {

    // 메인페이지 - 최신 판매글
    List<Post> findLatestPosts();

    // 메인페이지 - 관심 판매글
/*    List<Post> findLikedPosts();*/

}
