package com.lec.spring.notice.controller;

import com.lec.spring.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @MessageMapping("/Notice")
    public void NoticeaddiTion()
    {

    }

}
