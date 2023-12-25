package com.lec.spring.controller;

import com.lec.spring.domain.Notice;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.NoticeService;
import com.lec.spring.service.PostService;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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
    public void post(@RequestParam(required = false) String type,
                       @RequestParam(required = false) String keyword,
                       Integer page,
                       Model model) {
        postService.findPost(page, model, type, keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);

    }

    @GetMapping("/notice")
    public void notice(){}


    @PostMapping("/PostsData")
    public ResponseEntity<List<Post>> PostsData(Principal principal) {
        String currentUsername = principal.getName();
        User user = userService.getUserByUsername(currentUsername);
        Long userId = user.getId();

        List<Post> Posts = userService.Posts(userId);

        return ResponseEntity.ok(Posts);
    }

    @PostMapping("/deletePosts")
    public ResponseEntity<String> deletePosts(@RequestBody Map<String, List<Long>> requestBody) {
        List<Long> selectedPostIds = requestBody.get("selectedPostIds");

        if (selectedPostIds != null && !selectedPostIds.isEmpty()) {
            postService.deleteMyPosts(selectedPostIds);
            return ResponseEntity.ok("삭제가 완료되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("삭제할 항목을 선택해주세요.");
        }
    }



}
