package com.lec.spring.service;

import com.lec.spring.domain.Notice;

import java.util.List;

public interface NoticeService {

    Notice findByUserId(Long id);

    void checkView();

    int createNotice(Notice notice);

    // 어드민 페이지용
    List<Notice> getAllNotice();

    List<Notice> getLatestNotice();

}
