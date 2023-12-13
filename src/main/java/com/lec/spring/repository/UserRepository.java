package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

    // id 값을 통해 user 를 리턴
    User findById(Long id);

    // 회원의 아이디로 user 정보 리턴
    User findByUsername(@Param("username") String username);

    // 신규 user 등록
    int join(User user);

    // 마이페이지 - 프로필 수정
    void updatePassword(
            @Param("newPassword") String newPassword,
            @Param("username") String username
    );

    void updatePhoneNumber(
            @Param("newPhoneNumber") String newPhoneNumber,
            @Param("username") String username
    );

    void updateEmailAddress(
            @Param("newEmailAddress") String newEmailAddress,
            @Param("username") String username
    );

    // 마이페이지 - 회원 탈퇴
    void deleteAccount(
            @Param("username") String username
    );

}
