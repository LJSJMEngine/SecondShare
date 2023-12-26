package com.lec.spring.service;

import com.lec.spring.domain.Notice;
import com.lec.spring.repository.ChatRepository;
import com.lec.spring.repository.NoticeRepository;
import com.lec.spring.repository.PostRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeRepository noticeRepo;

    @Autowired
    public NoticeServiceImpl(SqlSession sqlss){
        this.noticeRepo = sqlss.getMapper(NoticeRepository.class);
        System.out.println("[SERVICEIMPL] NoticeServiceImpl Init");
    }
    @Override
    public List<Notice> findByUserId(Long id) {
        return noticeRepo.findByUserId(id);
    }

    @Override
    public void checkView(Long user_id) {
        noticeRepo.checkView(user_id);

    }


    @Override
    public int createNotice(Notice notice) {
        //todo
        //알림 생성
        noticeRepo.createNotice(notice);
        return 0;
    }

    // 어드민 페이지용

}
