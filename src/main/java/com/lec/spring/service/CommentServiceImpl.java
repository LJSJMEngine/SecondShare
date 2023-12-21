package com.lec.spring.service;

import com.lec.spring.domain.Comment;
import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.domain.User;
import com.lec.spring.repository.CommentRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(SqlSession sqlSession){
        commentRepository = sqlSession.getMapper(CommentRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
    }

    @Override
    public QryCommentList list(Long id) {
        QryCommentList list = new QryCommentList();

        List<Comment> comments = commentRepository.findByPost(id);

        list.setCount(comments.size());
        list.setList(comments);
        list.setStatus("OK");

        return list;
    }

    @Override
    public QryResult write(Long post_id, Long user_id, String content) {
        User user = userRepository.findById(user_id);

        Comment comment = Comment.builder()
                .user(user)
                .content(content)
                .post_id(post_id)
                .build();

        commentRepository.save(comment);

        QryResult result = QryResult.builder()
                .count(1)
                .status("OK")
                .build();

        return result;
    }

    @Override
    public QryResult delete(Long id) {
        int count = commentRepository.deleteById(id);

        String status = ((count > 0 ) ? "OK" : "FAIL");

        QryResult result = QryResult.builder()
                .count(count)
                .status(status)
                .build();

        return result;
    }
}
