package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserRepository {

    // id 값을 통해 user 를 리턴
    User findById(@Param("id") Long id);

    // 회원의 아이디로 user 정보 리턴
    User findByUsername(@Param("username") String username);

    List<User> findAll();

    List<User> findLatestUser();

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

    // 마이페이지 - 판매물품
    List<Post.MyPosts> showMyPosts(Long id);

    // 메인페이지 - 내 관심물품
    Long findUserIdByUsername(@Param("username") String username);

    // 어드민 페이지 회원 검색

    // 검색 결과 전체 개수
    int countUserResult(@Param("keyword") String keyword, @Param("type") String type);

    // 결과를 페이징해서 가져오기
    List<User> searchWithPaging(@Param("type") String type, @Param("keyword") String keyword,
                                @Param("from") int from, @Param("rows") int rows);
}
