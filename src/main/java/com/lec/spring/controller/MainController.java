package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    private final PostService postService;

    @Autowired
    public MainController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public String redirectToMain() {
        return "redirect:/main";
    }

    @RequestMapping("/main")
    public String main(Model model, Authentication authentication) {
        // 사용자 정보 추가
        addUserInfo(model, authentication);

        // 최신 판매글 가져오기 (이미지 경로 추가)
        List<Map<String, Object>> latestPosts = postService.getLatestPostsWithUsernameAndImgPath();
        model.addAttribute("latestPosts", latestPosts);

        // 로그인한 경우에만 관심 판매글 가져오기
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            // 관심 판매글 가져오기 (이미지 경로 및 작성자 사용자명 추가)
            // model.addAttribute("likedPosts", likedPosts);
        }

        return "main";
    }

    // 사용자 정보를 모델에 추가하는 메소드
    private void addUserInfo(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User.CurrentUser currentUser = new User.CurrentUser(username);
            model.addAttribute("currentUser", currentUser);
        }
    }
}
