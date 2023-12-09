package com.lec.spring.controller;

import com.lec.spring.service.BoardService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
   private BoardService boardService;

    @GetMapping("/write")
    public void write(){}

    @PostMapping("/write")
    public String writeOk(){
        return "/writeOk";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        model.addAttribute("post" ,boardService.detail(id));
        return "board/detail";
    }
    @GetMapping("modify/{id}")
    public String  modify(@PathVariable Long id, Model model){
        model.addAttribute("post",boardService.selectByPostId(id));
        return "board/modify";
    }
    @PostMapping("modify")
    public String modifyOk(){
        return "/modifyOk";
    }

//    @GetMapping("review")
}
