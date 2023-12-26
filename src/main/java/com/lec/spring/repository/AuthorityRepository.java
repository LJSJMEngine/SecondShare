package com.lec.spring.repository;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;

import java.util.List;

public interface AuthorityRepository {

        // 회원 이름으로 권한에 대한 정보 불러오기
        Authority findByName(String name);

        // 특정 회원의 권한 불러오기
        List<Authority> findByUser(User user);

        // 특정 회원에 대해 권한 부여하기
        int addAuth(Long userId, Long authId);

        // 권한 변경하기
        int chAuth(Long user_id, Long authority_id);

}
