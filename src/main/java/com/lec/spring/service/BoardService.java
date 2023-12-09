package com.lec.spring.service;

import com.lec.spring.domain.Post;

public interface BoardService {
    int write(Post post);

    Post detail(Long id);

    Post selectByPostId(Long id);

    int modify(Post post);


}
