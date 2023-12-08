package com.lec.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/")
    public String main(Model model) {return "redirect:/main";}

    @RequestMapping("/main")
    public void main() {}
}
