package com.lec.spring.service;

import com.lec.spring.domain.Notice;

import java.util.List;

public interface NoticeService {

    List<Notice> findByUserId(Long id);

    void checkView(Long user_id);

    int createNotice(Notice notice);

}
