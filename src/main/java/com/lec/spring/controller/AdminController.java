package com.lec.spring.controller;

import com.lec.spring.service.BoardService;
import com.lec.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/home")
    public void home(){}

    @GetMapping("/user")
    public void user(){}

    @GetMapping("/post")
    public void post(Model model, Long id ){


        model.addAttribute("post" , boardService.post(id));
    }

    @GetMapping("/notice")
    public void notice(){}
}
