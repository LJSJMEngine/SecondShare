package com.lec.spring.service;

import com.lec.spring.domain.Heart;

public interface HeartService {


    int addHeart(Heart heart) throws Exception;

    Heart FindByUser(Long user_id);
    Heart FindByPost(Long post_id);






}
