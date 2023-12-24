package com.lec.spring.repository;

import com.lec.spring.domain.Notice;
import com.lec.spring.domain.Post;

import java.util.List;

public interface NoticeRepository {

    Notice findByUserId(Long id);

    void checkView();

    int createNotice(Notice notice);


    // 어드민 페이지 - 공지 조회
    List<Notice> findAll();

    List<Notice> findLatestNotice();
}
