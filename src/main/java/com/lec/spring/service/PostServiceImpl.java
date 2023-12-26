package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.repository.PostRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;



    @Autowired
    public PostServiceImpl(SqlSession sqlss){
        this.postRepository = sqlss.getMapper(PostRepository.class);
        System.out.println("[SERVICEIMPL] PostServiceImpl Init");
    }


    // 마이페이지 - 내 판매글 삭제하기
    @Override
    public Post getPostByPostId(Long post_id) {
        return postRepository.findByPostId(post_id);
    }

    @Override
    @Transactional
    public void deleteMyPosts(List<Long> selectedPostIds) {
        postRepository.updatePostStatus(selectedPostIds);
    }

    // 어드민 용
    @Override
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @Override
    public List<Post> getLatestPosts(){
        return postRepository.findLatest();
    }

    // 마이페이지 - 최신 판매글
    @Override
    public List<Map<String, Object>> getLatestPostsWithUsernameAndImgPath() {
        List<Map<String, Object>> latestPosts = postRepository.findLatestPostsWithUsernameAndSampleImg();

        // 중복된 post_id를 제거
        latestPosts = latestPosts.stream()
                .collect(Collectors.toMap(post -> post.get("post_id"), post -> post, (existing, replacement) -> existing))
                .values().stream()
                .collect(Collectors.toList());

        for (Map<String, Object> post : latestPosts) {
            Integer postId = (Integer) post.get("post_id");
             String filename = (String) post.get("filename");

            // 이미지 경로 생성 및 추가
            String imgPath = getFirstImgPathByPostId(postId);
            post.put("img_path", imgPath);

        }

        return latestPosts;
    }

    private String getFirstImgPathByPostId(Integer postId) {
        List<String> imagePaths = getImagePathsByPostId(postId);
        if (!imagePaths.isEmpty()) {
            return imagePaths.get(0);
        }
        return "/sample.jpg";
    }

    public List<String> getImagePathsByPostId(Integer postId) {
        return postRepository.getImagePathsByPostId(postId);
    }

    // 마이페이지 - 관심 판매글
    @Override
    public List<Map<String, Object>> findLikedPostsByUserId(Long userId) {
        List<Map<String, Object>> likedPosts = postRepository.findLikedPostsByUserId(userId);

        // 중복된 post_id를 제거
        likedPosts = likedPosts.stream()
                .collect(Collectors.toMap(post -> post.get("post_id"), post -> post, (existing, replacement) -> existing))
                .values().stream()
                .collect(Collectors.toList());

        for (Map<String, Object> post : likedPosts) {
            Integer postId = (Integer) post.get("post_id");
            String attachmentFilename = (String) post.get("attachment_filename");

            // 이미지 경로 생성 및 추가
            String imgPath = getFirstImgPathByPostId(postId);
            post.put("img_path", imgPath);
        }

        return likedPosts;
    }

    @Override
    public List<Post> findPost(Long id) {
        return postRepository.findPost(id);
    }

}