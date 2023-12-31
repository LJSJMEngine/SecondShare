package com.lec.spring.controller;

import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public QryCommentList list(Long id) { return  commentService.list(id); }

    @PostMapping("/write")
    public QryResult write(
            @RequestParam("id") Long post_id,
            @RequestParam("user_id") Long user_id,
            String content){
        return commentService.write(post_id, user_id, content);
    }

    @PostMapping("/chatButton")
    public QryResult chatBtnCreate(
            @RequestParam("id") Long post_id,
            @RequestParam("user_id") Long user_id,
            String content){
        return commentService.write(post_id, user_id, content);
    }

    @PostMapping("/delete")
    public QryResult delete(Long id) { return commentService.delete(id); }
}
