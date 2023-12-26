package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.Notice;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.service.NoticeService;
import com.lec.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    @Autowired
    private final NoticeService noticeService;

    private final PostService postService;
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/Notice")
    public Notice NoticeAddition(Notice notice,  Authentication authentication)
    {
        System.out.println(notice);
        Post p = postService.getPostByPostId((long) notice.getPost_id());
        notice.setSubject(p.getSubject()+ " 게시글의 " + notice.getSubject());
        noticeService.createNotice(notice);

        messagingTemplate.convertAndSend("/sub/notice/" + notice.getUser_id(), notice);
        return notice;
    }
    @MessageMapping("/NoticeUpdateRead")
    public void NoticeReadUpdate(Notice notice, Authentication authentication)
    {
        PrincipalDetails pDetails = (PrincipalDetails)authentication.getPrincipal();
        User currentUser = pDetails.getUser();
        noticeService.checkView(currentUser.getId());
    }
    @MessageMapping("/NoticeInit")
    public Notice NoticeInstanciate(Notice notice, Authentication authentication) throws InterruptedException {

        PrincipalDetails pDetails = (PrincipalDetails)authentication.getPrincipal();
        User currentUser = pDetails.getUser();
        if(currentUser.getId() != notice.getUser_id()) {
            System.out.println("[NOTICE ERROR] : 현재 로그인 된 아이디와 알림 요청온 아이디가 서로 다름");
            return notice;
        }
        List<Notice> noticeList = noticeService.findByUserId(currentUser.getId());

        Thread.sleep(100);
        boolean isReadCheck = true;
        for(Notice currentNotice : noticeList)
        {
            Thread.sleep(10);
            messagingTemplate.convertAndSend("/sub/notice/" + currentUser.getId(),currentNotice);
            if(!currentNotice.isReadChk())
                isReadCheck = false;
        }
        Notice resultNotice = new Notice();
        resultNotice.setStatus(-1);
        resultNotice.setReadChk(isReadCheck);
        messagingTemplate.convertAndSend("/sub/notice/" + currentUser.getId(),resultNotice);
        return notice;
    }

}
