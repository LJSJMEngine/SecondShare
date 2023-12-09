package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.repository.PostRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class BoardServiceImpl implements BoardService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public BoardServiceImpl(SqlSession sqlSession){
        userRepository = sqlSession.getMapper(UserRepository.class);
        postRepository = sqlSession.getMapper(PostRepository.class);
        System.out.println("BoardService 생성");
    }

    @Override
    public int write(Post post) {
        return 0;
    }

    @Override
    @Transactional
    public Post detail(Long id) {
        postRepository.incViewCnt(id);
        Post post = postRepository.findByPostId(id);


        return post;
    }

    @Override
    public Post selectByPostId(Long id) {
        Post post = postRepository.findByPostId(id);
        return post;
    }

    @Override
    public int modify(Post post) {
        return 0;
    }
}
