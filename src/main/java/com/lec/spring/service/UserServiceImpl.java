package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.ReviewRepository;
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

import java.util.ArrayList;
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
    private ReviewRepository reviewRepository;


    @Autowired
    public UserServiceImpl(SqlSession sqlSession) {
        userRepository = sqlSession.getMapper(UserRepository.class);
        authorityRepository = sqlSession.getMapper(AuthorityRepository.class);
        reviewRepository = sqlSession.getMapper(ReviewRepository.class);
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

    // 어드민 페이지
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAllButAdmin();
    }

    @Override
    public List<User> getLatestUser(){
        return userRepository.findLatestUser();
    }

    @Override
    public List<User> userList() {
        return userRepository.findAll();
    }

    @Override
    public List<User> userList(Integer page, Model model, String type, String keyword) {
        if (page == null) page = 1;
        if (page < 1) page = 1;

        HttpSession session = U.getSession();
        Integer writrPages = (Integer) session.getAttribute("writePages");
        if (writrPages == null) writrPages = WRITE_PAGES;
        Integer pageRows = (Integer) session.getAttribute("pageRows");
        if (pageRows == null) pageRows = PAGE_ROWS;
        session.setAttribute("page", page);

        long cnt = userRepository.countUserResult(keyword, type);
        int totalPage = (int) Math.ceil(cnt / (double) pageRows);

        int startPage = 0;
        int endPage = 0;

        List<User> list = null;

        if (cnt > 0) {
            if (page > totalPage) page = totalPage;

            int fromRow = (page - 1) * pageRows;

            startPage = (((page - 1) / writrPages) * writrPages) + 1;
            endPage = startPage + writrPages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            list = userRepository.searchWithPaging(type, keyword, (page - 1)* pageRows, pageRows);
            model.addAttribute("list", list);
        } else {
            page = 0;
        }

        model.addAttribute("cnt", cnt);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageRows", pageRows);

        // paging
        model.addAttribute("url", U.getRequest().getRequestURI());
        model.addAttribute("writePages", writrPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return list;
    }


    @Override
    public List<Post> Posts(Long id) {
        try {
            return userRepository.Posts(id);
        } catch (Exception e) {
            // 예외가 발생한 경우 로그 출력
            e.printStackTrace();
            return Collections.emptyList(); // 빈 리스트 반환
        }
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

    @Override
    public List<Post> findUserPosts(Long id, Model model) {
        long cnt = userRepository.userpostcountAll(id);   // 글 목록 전체의 개수
        long statuscnt = userRepository.userpoststatuscount(id); // 판매완료 개수
        List<Post> userPosts = userRepository.selectFromRow(id);
        model.addAttribute("userPosts", userPosts);
        model.addAttribute("cnt", cnt);  // 전체 글 개수
        model.addAttribute("statuscnt",statuscnt); // 판매글 개수

        return userPosts;
    }


}
