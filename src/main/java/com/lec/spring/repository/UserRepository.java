package com.lec.spring.repository;

import com.lec.spring.domain.User;

public interface UserRepository {
    int save(User user);

    int update(Long id);

    // id 값을 통해 user 를 리턴
    User findById(Long id);

    // 회원의 아이디로 user 정보 리턴
    User findByUsername(String username);

    // 신규 user 등록
    int join(User user);

    // 회원 정보 수정
    int update(User user);

}
