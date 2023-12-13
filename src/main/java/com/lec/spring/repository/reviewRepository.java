package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.Review;

import java.util.List;

public interface reviewRepository {
    int save(Review review);


}
