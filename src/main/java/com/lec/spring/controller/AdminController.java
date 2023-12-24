package com.lec.spring.controller;

import com.lec.spring.domain.Notice;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.NoticeService;
import com.lec.spring.service.PostService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        // 모든 유저, 게시글, 공지들을 List에 담음
        List<User> users = userService.getLatestUser();
        List<Post> posts = postService.getLatestPosts();
        List<Notice> notices = noticeService.getLatestNotice();

        // 담은 값들을 model 에 담아 보냄
        model.addAttribute("posts", posts);
        model.addAttribute("users", users);
        model.addAttribute("notices", notices);

        return "admin/home";
    }

    @GetMapping("/user")
    public void user(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String keyword,
            Integer page,
            Model model){
        userService.userList(page, model, type, keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);
    }

    @GetMapping("/post")
    public void post(){}

    @GetMapping("/notice")
    public void notice(){}

    @PostMapping("/userPageRows")
    public String userPageRows(Integer page, Integer pageRows){
        U.getSession().setAttribute("pageRows", pageRows);
        return "redirect:/admin/user?page=" + page;
    }
}
