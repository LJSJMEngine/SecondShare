package com.lec.spring.service;

import com.lec.spring.domain.Notice;

import java.util.List;

public interface NoticeService {

    List<Notice> findByUserId(Long id);

    void checkView(Long user_id);

    int createNotice(Notice notice);

    // 어드민 페이지용
    //List<Notice> getAllNotice();

    //List<Notice> getLatestNotice();

}
