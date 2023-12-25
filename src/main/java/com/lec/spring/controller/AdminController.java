package com.lec.spring.controller;

import com.lec.spring.domain.Notice;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.NoticeService;
import com.lec.spring.service.PostService;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private PostService postService;
    private NoticeService noticeService;

    @Autowired
    public AdminController(UserService userService, PostService postService, NoticeService noticeService) {
        this.userService = userService;
        this.postService = postService;
        this.noticeService = noticeService;
    }

    @GetMapping("/home")
    public String home(Model model){

        return "admin/home";
    }

    @GetMapping("/user")
    public void user(){}

    @GetMapping("/post")
    public String post(Model model,Long id) {
        List<Post> adminlist = postService.findPost(id);
        model.addAttribute("adminlist", adminlist);
        return "admin/post";
    }

    @GetMapping("/notice")
    public void notice(){}
}
