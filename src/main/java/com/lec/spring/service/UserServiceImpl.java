package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;
    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

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
        return userRepository.findByUsername(username);
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
    public Long findUserIdByUsername(String username) {
        return userRepository.findUserIdByUsername(username);
    }


    @Override
    public User userpage(Long id) {
        User user = userRepository.findById(id);
        return user;
    }
//    페이징
    @Override
    public List<Post> findUserPosts(Long id, Model model, Integer page) {
        // 현재 페이지 parameter
        if(page == null) page = 1;   // 디폴트 1 page
        if(page < 1) page = 1;

        // 페이징
        // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
        // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer) session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS; // 만약 session 에 없으면 기본값으로 동작
        session.setAttribute("page", page);  // 현재 페이지 번호 -> session 에 저장

        long cnt = userRepository.userpostcountAll(id);   // 글 목록 전체의 개수
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);  // 총 몇 '페이지' 분량인가?

        // [페이징] 에 표시할 '시작페이지' 와 '마지막 페이지'
        int startPage = 0;
        int endPage = 0;
        pageRows = 5;
        // 해당 페이지의 글 목록
        List<Post> userPosts = null;
        if(cnt > 0){  // 데이터가 최소 1개 이상 있는 경우만 페이징
            // page 값 보정
            if(page > totalPage) page = totalPage;

            // 몇번째 데이터부터 fromRow
            int fromRow = (page - 1) * pageRows;

            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            // 해당 페이지의 글 목록 읽어오기
            userPosts= userRepository.selectFromRow(id,fromRow, pageRows);
            model.addAttribute("userPosts", userPosts);
        } else {
            page = 0;
        }
        model.addAttribute("cnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

        // [페이징]
        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

        return userPosts;
    }


}
