package com.lec.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    @RequestMapping("chatMake")
    @ResponseBody
    public String chatMake (Model model)
    {
        model.addAttribute("chatName",1);

        return "chat/chatMake";
    }
    @RequestMapping("/text001")
    public String  text001(Model m)
    {
        return "chat/text001";
    }

}
