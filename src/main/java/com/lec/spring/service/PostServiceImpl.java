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
        postRepository.deletePostsByIds(selectedPostIds);
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
}