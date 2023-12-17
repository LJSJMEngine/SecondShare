package com.lec.spring.service;

import com.lec.spring.domain.Post;

import java.util.List;

public interface PostService {

    // 메인페이지 - 최신 판매글
    List<Post> getLatestPosts();

    // 마이페이지 - 관심 판매글
/*    List<Post> getLikedPosts();*/
}
