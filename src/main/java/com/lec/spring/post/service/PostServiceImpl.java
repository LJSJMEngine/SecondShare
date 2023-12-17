package com.lec.spring.post.service;

import com.lec.spring.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 마이페이지 - 최신 판매글
    @Override
    public List<Map<String, Object>> getLatestPostsWithUsernameAndImgPath() {
        List<Map<String, Object>> latestPosts = postRepository.findLatestPostsWithUsername();

        for (Map<String, Object> post : latestPosts) {
            Integer postId = (Integer) post.get("post_id");
            String filename = (String) post.get("filename");

            // 이미지 경로 생성 및 추가
            String imgPath = "/img/attachment/" + postId + "/" + filename;
            post.put("img_path", imgPath);
        }

        return latestPosts;
    }

    // 마이페이지 - 관심 판매글
    @Override
    public List<Map<String, Object>> getLikedPostsWithUsernameAndImgPath(String username) {
        List<Long> likedPostIds = postRepository.findLikedPostIdsByUsername(username);
        List<Map<String, Object>> likedPosts = postRepository.findPostsByIds(likedPostIds);

        for (Map<String, Object> post : likedPosts) {
            Long postId = (Long) post.get("post_id");
            String filename = (String) post.get("filename");

            // 이미지 경로 생성 및 추가
            String imgPath = "/img/attachment/" + postId + "/" + filename;
            post.put("img_path", imgPath);
        }

        return likedPosts;
    }
}