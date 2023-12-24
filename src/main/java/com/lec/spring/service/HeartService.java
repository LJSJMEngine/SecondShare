package com.lec.spring.service;

import com.lec.spring.domain.Heart;
import com.lec.spring.domain.HeartRequest;

import java.util.List;

public interface HeartService {

    void insertHeart(Heart heart);

    void updateHeart(Heart heart);

    List<Heart> findHeartsByUserId(int user_id);

    int countHeartsByPostId(int post_id);

    Heart processHeartRequest(HeartRequest heartRequest);
    boolean hasUserLikedPost(int user_id, int post_id);
}
