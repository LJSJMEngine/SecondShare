package com.lec.spring.service;

import com.lec.spring.domain.Notice;
import com.lec.spring.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    private ChatRepository chatRepo;

    @Override
    public Notice findByUserId(Long id) {
        //todo
        //유저 아이디로 알림 찾기.
        return null;
    }

    @Override
    public void checkView() {
        //todo
        //알림 봤습니다.

    }

    @Override
    public int createNotice(Notice notice) {
        //todo
        //알림 생성

        return 0;
    }
}
