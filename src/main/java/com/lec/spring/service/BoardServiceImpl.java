package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.repository.CategoryRepository;
import com.lec.spring.repository.PostRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {


    private UserRepository userRepository;


   private PostRepository postRepository;


   private CategoryRepository categoryRepository;

    @Autowired
    public BoardServiceImpl(SqlSession sqlSession){  // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        postRepository = sqlSession.getMapper(PostRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
        categoryRepository = sqlSession.getMapper(CategoryRepository.class);

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

    public List<Post> search(String keyword, Long tag) {
        return postRepository.search(keyword, tag);
    }

//    public List<Post> searchByCategory(String tag) {
//        return postRepository.searchByCategory(tag);
//    }



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
