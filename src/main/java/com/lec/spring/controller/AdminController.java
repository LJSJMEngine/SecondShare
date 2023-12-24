package com.lec.spring.controller;

import com.lec.spring.service.BoardService;
import com.lec.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public void home(){}

    @GetMapping("/user")
    public void user(){}

    @GetMapping("/post")
    public void post(){}

    @GetMapping("/notice")
    public void notice(){}
}
