package com.lec.spring.controller;

import com.lec.spring.domain.Notice;
import com.lec.spring.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    @Autowired
    private final NoticeService noticeService;

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/Notice")
    public Notice NoticeAddition(Notice notice)
    {

        messagingTemplate.convertAndSend("/sub/notice/" + notice.getUser_id(),notice);
        return notice;

    }

}
