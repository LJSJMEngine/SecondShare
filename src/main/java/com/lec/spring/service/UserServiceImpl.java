package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;


    @Autowired
    public UserServiceImpl(SqlSession sqlSession) {
        //userRepository = sqlSession.getMapper(UserRepository.class);
        //authorityRepository = sqlSession.getMapper(AuthorityRepository.class);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean idExist(String username) {
        User user = findByUsername(username);
        return (user != null);
    }

    @Override
    public int register(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.join(user);

        Authority authority = authorityRepository.findByName("MEMBER");

        Long userId = user.getId();
        Long authId = authority.getId();

        authorityRepository.addAuth(userId, authId);

        return 1;
    }


    @Override
    public List<Authority> selectAuthById(Long id) {
        User user = userRepository.findById(id);
        return authorityRepository.findByUser(user);
    }
}
