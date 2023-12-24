package com.lec.spring.repository;

import com.lec.spring.domain.Heart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HeartRepository {

    void insertHeart(Heart heart);

    void updateHeart(Heart heart);

    List<Heart> findHeartsByUserId(int user_id);

    int countHeartsByPostId(int post_id);

    Heart findHeartByUserAndPost(int user_id, int post_id);
}
