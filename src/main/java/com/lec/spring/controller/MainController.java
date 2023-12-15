package com.lec.spring.controller;

import com.lec.spring.domain.Post;
import com.lec.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public String main(Model model) {
        // 최신 판매글 가져오기
        List<Post> latestPosts = postService.getLatestPosts();
        model.addAttribute("latestPosts", latestPosts);

        // 관심 판매글 가져오기
/*        List<Post> likedPosts = postService.getLikedPosts();
        model.addAttribute("likedPosts", likedPosts);*/

        return "main";
    }
}