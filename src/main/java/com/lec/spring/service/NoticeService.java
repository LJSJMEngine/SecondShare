package com.lec.spring.service;

import com.lec.spring.domain.Notice;

public interface NoticeService {

    Notice findByUserId(Long id);

    void checkView();

    int createNotice(Notice notice);

    // 어드민 페이지용


}
