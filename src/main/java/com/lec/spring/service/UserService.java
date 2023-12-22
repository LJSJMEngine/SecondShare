package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;

import java.util.List;

public interface UserService {

    // ID 로 User 정보 불러오기
    User findByUsername (String username);
    User findById (Long id);

    // 존재하는 id 인지 확인
    boolean idExist(String username);

    // 회원 가입 (신규 등록)
    int register(User user);

    // id에 따른 사용자의 권한
    List<Authority> selectAuthById(Long id);

    // 다른 유저 페이지
    User userpage(Long id);

    // 마이페이지 - 프로필 보기, 프로필 수정, 판매물품
    User getUserByUsername(String username);
    User getUserById(Long id);
    void deleteAccount(String username);
    void updatePassword(String newPassword, String username);
    void updatePhoneNumber(String newPhoneNumber, String username);
    void updateEmailAddress(String newEmailAddress, String username);
    List<Post.MyPosts> showMyPosts(Long id);
    void deleteAllMyPosts(Long id);

    boolean authenticateUser(String username, String password);
}

