package com.lec.spring.service;

import com.lec.spring.domain.Heart;
import com.lec.spring.domain.HeartRequest;
import com.lec.spring.repository.HeartRepository;
import com.lec.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HeartServiceImpl implements HeartService {

    private final PostRepository postRepository;
    private final HeartRepository heartRepository;

    @Autowired
    public HeartServiceImpl(PostRepository postRepository, HeartRepository heartRepository) {
        this.postRepository = postRepository;
        this.heartRepository = heartRepository;
    }

    @Override
    public void insertHeart(Heart heart) {
        heartRepository.insertHeart(heart);
    }

    @Override
    public void updateHeart(Heart heart) {
        heartRepository.updateHeart(heart);
    }

    @Override
    public List<Heart> findHeartsByUserId(int user_id) {
        return heartRepository.findHeartsByUserId(user_id);
    }

    @Override
    public int countHeartsByPostId(int post_id) {
        return heartRepository.countHeartsByPostId(post_id);
    }

    @Override
    public Heart processHeartRequest(HeartRequest heartRequest) {
        // 중복 좋아요 확인 (중복 불가)
        Heart existingHeart = heartRepository.findHeartByUserAndPost(heartRequest.getUser_id(), heartRequest.getPost_id());

        if (existingHeart == null) {
            // 중복 좋아요가 아닌 경우
            Heart newHeart = Heart.builder()
                    .user_id(heartRequest.getUser_id())
                    .post_id(heartRequest.getPost_id())
                    .is_active(true)
                    .created_at(LocalDateTime.now())
                    .build();

            heartRepository.insertHeart(newHeart);
        } else {
            // 중복 좋아요인 경우
            existingHeart.setIs_active(!existingHeart.isIs_active());
            existingHeart.setUpdated_at(LocalDateTime.now());
            heartRepository.updateHeart(existingHeart);
        }

        return heartRequest.toHeart();  // HeartRequest를 Heart로 변환하여 반환
    }

    @Override
    public boolean hasUserLikedPost(int user_id, int post_id) {
        Heart existingHeart = heartRepository.findHeartByUserAndPost(user_id, post_id);
        return existingHeart != null && existingHeart.isIs_active();
    }
}