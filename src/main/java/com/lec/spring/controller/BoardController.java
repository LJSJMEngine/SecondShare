package com.lec.spring.controller;

import com.lec.spring.service.BoardService;
import org.springframework.security.core.parameters.P;
import com.lec.spring.domain.Post;
import com.lec.spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;



//    @GetMapping("/board/list")
//    public void list(Model model){
//        boardService.list(model);
//
//    }

    @GetMapping("/board/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Post> list;

        if (StringUtils.hasText(keyword)) {
            list = boardService.search(keyword);
        } else {
            list = boardService.list();
        }

        model.addAttribute("list", list);
        return "board/list";
    }

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
