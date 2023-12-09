package com.lec.spring.controller;

import com.lec.spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;



    @GetMapping("/board/list")
    public void list(Model model){
        boardService.list(model);

    }

    @GetMapping("/board/write")
    public void write(){}






}
