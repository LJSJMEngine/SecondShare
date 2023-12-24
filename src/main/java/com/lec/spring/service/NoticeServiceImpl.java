package com.lec.spring.service;

import com.lec.spring.domain.Notice;
import com.lec.spring.repository.ChatRepository;
import com.lec.spring.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private ChatRepository chatRepository;

    @Autowired
    private NoticeRepository noticeRepository;

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

    // 어드민 페이지용
    @Override
    public List<Notice> getAllNotice() {
        return noticeRepository.findAll();
    }

    @Override
    public List<Notice> getLatestNotice() {
        return noticeRepository.findLatestNotice();
    }
}
