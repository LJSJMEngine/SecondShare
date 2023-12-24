package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.Heart;
import com.lec.spring.domain.HeartRequest;
import com.lec.spring.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/board/detail")
public class HeartController {
    private HeartService heartService;

    @Autowired
    public HeartController(HeartService heartService) {
        this.heartService = heartService;
    }

    @GetMapping("/currentUserId")
    public ResponseEntity<Long> getCurrentUserId(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long currentUserId = principalDetails.getUser().getId();
            return ResponseEntity.ok(currentUserId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/heart/{post_id}")
    public ResponseEntity<Heart> processHeartRequest(
            @PathVariable Integer post_id,
            @RequestBody HeartRequest heartRequest) {
        try {
            // post_id 가 경로와 본문에서 일치하는 지 확인
            if (!post_id.equals(heartRequest.getPost_id())) {
                return ResponseEntity.badRequest().build();
            }

            // 현재 로그인한 사용자 id 가져오기
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Long currentUserId = ((PrincipalDetails) authentication.getPrincipal()).getUser().getId();

            // 현재 요청된 게시글의 post_id 가져오기
            Integer currentPostId = post_id;

            // HeartRequest 객체와 Heart 객체를 업데이트하거나 생성하여 반환
            HeartRequest updatedHeartRequest = updateHeartRequest(heartRequest, currentUserId, currentPostId);
            Heart updatedHeart = heartService.processHeartRequest(updatedHeartRequest);

            int heartCount = heartService.countHeartsByPostId(currentPostId);
            return ResponseEntity.ok(new Heart(
                    updatedHeart.getId(),
                    updatedHeart.getUser_id(),
                    updatedHeart.getPost_id(),
                    updatedHeart.getIs_active(),
                    updatedHeart.getCreated_at(),
                    updatedHeart.getUpdated_at()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Heart(false, "좋아요 처리 중 오류가 발생했습니다. 원인: " + e.getMessage(), 0));
        }
    }

    private HeartRequest updateHeartRequest(HeartRequest heartRequest, Long currentUserId, Integer currentPostId) {
        heartRequest.setUser_id(Math.toIntExact(currentUserId));
        heartRequest.setPost_id(currentPostId);

        return heartRequest;
    }
}
