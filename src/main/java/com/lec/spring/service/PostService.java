package com.lec.spring.service;

import com.lec.spring.domain.Post;

import java.util.List;
import java.util.Map;

public interface PostService {

    // 마이페이지 - 내 판매글 삭제하기
    Post getPostByPostId(Long post_id);
    void deleteMyPosts(List<Long> selectedPostIds);

    // 메인페이지 - 최신 판매글
    List<Map<String, Object>> getLatestPostsWithUsernameAndImgPath();

    // 메인페이지 - 관심 판매글
    List<Map<String, Object>> findLikedPostsByUserId(Long userId);
}
