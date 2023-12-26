package com.lec.spring.service;

import com.lec.spring.domain.Review;
import org.apache.ibatis.session.SqlSession;
import com.lec.spring.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(SqlSession sqlss){
        this.reviewRepository = sqlss.getMapper(ReviewRepository.class);
        System.out.println("[SERVICEIMPL] ReviewServiceImpl Init");
    }

    @Override
    public List<Review.MyReceivedReviews> findReviewsByUserId(int userId) {
        try {
            return reviewRepository.findReviewsByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Review> findReview(int userId) {
        try {
            return reviewRepository.findReview(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}