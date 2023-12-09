package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.repository.PostRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private PostRepository postRepository;

    @Autowired
    public BoardServiceImpl(SqlSession sqlSession){
        postRepository = sqlSession.getMapper(PostRepository.class);
        System.out.println("BoardService() 생성");
    }


    @Override
    public List<Post> list() {
        return postRepository.findAll();
    }


    @Override
    public List<Post> list(Model model) {

        List<Post> list = postRepository.findAll();
        model.addAttribute("list", list);
        return list;
    }

    public List<Post> search(String keyword) {
        return postRepository.search(keyword);
    }
}
