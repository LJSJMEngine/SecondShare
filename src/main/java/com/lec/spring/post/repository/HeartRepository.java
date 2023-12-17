package com.lec.spring.post.repository;

import com.lec.spring.domain.Heart;

import java.util.List;

public interface HeartRepository {

    int save(Heart heart);

    List<Heart> FindBuUserId(Long user_id);

    List<Heart> FindByPostId(Long post_id);

    List<Heart> FindByUserAndPost(Long user_id, Long post_id);



}
