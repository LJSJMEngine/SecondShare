package com.lec.spring.post.service;

import com.lec.spring.domain.Heart;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.post.repository.HeartRepository;
import com.lec.spring.post.repository.PostRepository;
import com.lec.spring.login.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class HeartServiceImpl implements HeartService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private HeartRepository heartRepository;

    @Autowired
    public HeartServiceImpl(SqlSession sqlSession){
        userRepository = sqlSession.getMapper(UserRepository.class);
        postRepository = sqlSession.getMapper(PostRepository.class);
        heartRepository = sqlSession.getMapper(HeartRepository.class);
        System.out.println("HeartService() 생성");
    }


    @Override
    public int addHeart(Heart heart) throws Exception {
        User user = userRepository.findById(heart.getUser_id());
        Post post = postRepository.findByPostId(heart.getPost_id());


        // TODO

        return 1;
    }

    @Override
    public Heart FindByUser(Long user_id) {
        return null;
    }

    @Override
    public Heart FindByPost(Long post_id) {
        return null;
    }
}
