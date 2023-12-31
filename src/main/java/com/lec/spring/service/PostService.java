package com.lec.spring.service;

import com.lec.spring.domain.Post;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface PostService {

    // 마이페이지 - 내 판매글 삭제하기
    Post getPostByPostId(Long post_id);
    void deleteMyPosts(List<Long> selectedPostIds);

    List<Post> getAllPosts();

    List<Post> getLatestPosts();

    // 메인페이지 - 최신 판매글
    List<Map<String, Object>> getLatestPostsWithUsernameAndImgPath();

    // 메인페이지 - 관심 판매글
    List<Map<String, Object>> findLikedPostsByUserId(Long userId);

    // 관리자페이지 - 게시글
    List<Post> findPost(Integer page, Model model, String keyword , String type);

    //관리자페이지 - 게시글 삭제
    void deletePosts(List<Long> selectedPostIds);

    void changeStatus(List<Long> selectedPostIds, Integer selectedStatus);
}
