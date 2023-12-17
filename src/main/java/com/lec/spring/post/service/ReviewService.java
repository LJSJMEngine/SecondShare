package com.lec.spring.post.service;

import com.lec.spring.domain.Review;

import java.util.List;

public interface ReviewService {

    // 마이페이지 - 내 리뷰 보기
    List<Review.MyReceivedReviews> findReviewsByUserId(int userId);
}
