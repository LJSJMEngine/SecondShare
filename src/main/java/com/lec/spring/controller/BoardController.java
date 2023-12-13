package com.lec.spring.controller;

import com.lec.spring.domain.Category;
import com.lec.spring.domain.Post;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String type,
                       @RequestParam(required = false) String keyword,
                       @RequestParam(required = false) Long tag,
                       Model model) {
        List<Post> list;

        if ("subject".equals(type)) {
            list = boardService.search(keyword);
        }
        else if ("id".equals(type)) {
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
