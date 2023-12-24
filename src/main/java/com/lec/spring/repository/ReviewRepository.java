package com.lec.spring.repository;

import com.lec.spring.domain.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewRepository {

    // 마이페이지 - 내 리뷰 보기
    List<Review.MyReceivedReviews> findReviewsByUserId(@Param("userId") int userId);

    // 유저페이지 리뷰 보기
    List<Review> findReview(@Param("userId") int userId);

}
