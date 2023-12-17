package com.lec.spring.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/adminhome")
    public void adminhome(){}

    @GetMapping("/usermng")
    public void usermng(){}

    @GetMapping("/postmng")
    public void postmng(){}

    @GetMapping("/categorymng")
    public void categorymng(){}

    @GetMapping("/notice")
    public void notice(){}
}
