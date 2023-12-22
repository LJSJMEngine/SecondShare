package com.lec.spring.repository;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;


public interface MemberMapper {
    public boolean selectUsername(String username);
}
