package com.lec.spring.repository;

import com.lec.spring.domain.Notice;
import com.lec.spring.domain.Post;

public interface NoticeRepository {

    Notice findByUserId(Long id);

    void checkView();

    int createNotice(Notice notice);

}
