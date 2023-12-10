package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;

import java.util.List;

public interface UserService {

    // ID 로 User 정보 불러오기
    User findByUsername (String username);

    // 존재하는 id 인지 확인
    boolean idExist(String username);

    // 회원 가입 (신규 등록)
    int register(User user);

    // id에 따른 사용자의 권한
    List<Authority> selectAuthById(Long id);
}

