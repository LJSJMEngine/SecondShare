package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 메인페이지 - 최신 판매글
    @Override
    public List<Post> getLatestPosts() {
        return postRepository.findLatestPosts();
    }

    // 마이페이지 - 관심 판매글
/*    @Override
    public List<Post> getLikedPosts() {
        return postRepository.findLikedPosts();
    }*/
}
