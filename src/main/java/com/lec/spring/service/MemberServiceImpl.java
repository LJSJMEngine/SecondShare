package com.lec.spring.service;

import com.lec.spring.repository.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(SqlSession sqlSession) {
        memberMapper = sqlSession.getMapper(MemberMapper.class);
    }

    @Override
    public boolean selectId(String id) {
        log.info("Service :: selectId..............");
        return memberMapper.selectId(id);
    }
}
