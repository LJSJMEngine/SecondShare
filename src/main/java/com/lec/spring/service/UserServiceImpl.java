package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;


    @Autowired
    public UserServiceImpl(SqlSession sqlSession) {
        userRepository = sqlSession.getMapper(UserRepository.class);
        authorityRepository = sqlSession.getMapper(AuthorityRepository.class);
        System.out.println(getClass().getName() + "() 생성");
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username.toUpperCase());
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean idExist(String username) {
        User user = findByUsername(username.toUpperCase());
        return (user != null);
    }

    @Override
    public int register(User user) {
        user.setUsername(user.getUsername().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.join(user);

        Authority authority = authorityRepository.findByName("ROLE_MEMBER");

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

    // 마이페이지 - 프로필 보기, 프로필 수정
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteAccount(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setStatus(1); // 회원 탈퇴 상태로 변경
            userRepository.deleteAccount(username); // 변경된 상태를 저장
        }
    }

    @Override
    public void updatePassword(String newPassword, String username) {
        // 비밀번호 유효성 검사
        if (!isValidPassword(newPassword)) {
            throw new IllegalArgumentException("유효하지 않은 비밀번호 형식입니다.");
        }
        String hashedPassword = passwordEncoder.encode(newPassword);
        userRepository.updatePassword(hashedPassword, username);
    }

    private boolean isValidPassword(String password) {
        // 최소 8자, 최대 22자 영문, 숫자, 특수문자 포함
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,22}$";
        return password.matches(passwordRegex);
    }

    @Override
    public void updatePhoneNumber(String newPhoneNumber, String username) {
        // 핸드폰 번호 유효성 검사
        if (!isValidPhoneNumber(newPhoneNumber)) {
            throw new IllegalArgumentException("유효하지 않은 핸드폰 번호 형식입니다.");
        }

        // 핸드폰 번호 변경
        userRepository.updatePhoneNumber(newPhoneNumber, username);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\d{3}-\\d{4}-\\d{4}$";
        return phoneNumber.matches(phoneRegex);
    }

    @Override
    public void updateEmailAddress(String newEmailAddress, String username) {
        // 핸드폰 번호 유효성 검사
        if (!isValidEmailAddress(newEmailAddress)) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다.");
        }

        // 핸드폰 번호 변경
        userRepository.updateEmailAddress(newEmailAddress, username);
    }

    private boolean isValidEmailAddress(String emailAddress) {
        // 이메일 정규식 (영문자와 숫자 포함)
        String emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]+$";
        return emailAddress.matches(emailRegex);
    }

    @Override
    public List<Post.MyPosts> showMyPosts(Long id) {
        try {
            return userRepository.showMyPosts(id);
        } catch (Exception e) {
            // 예외가 발생한 경우 로그 출력
            e.printStackTrace();
            return Collections.emptyList(); // 빈 리스트 반환
        }
    }

    @Override
    public void deleteAllMyPosts(Long id) {
        User user = userRepository.findById(id);

        if (user != null) {
            List<Post.MyPosts> myPosts = user.getMyPosts();

            myPosts.clear();    // myPosts 삭제
            userRepository.deleteAllMyPostsByUserId(id);    // 사용자의 모든 판매글 삭제
        }
    }


}
