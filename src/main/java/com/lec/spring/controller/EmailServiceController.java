package com.lec.spring.controller;

import com.lec.spring.domain.EmailAuthRequestDto;
import com.lec.spring.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmailServiceController {

    private final EmailService emailService;

    @PostMapping("/mailConfirm")
    public ResponseEntity<Map<String, String>> mailConfirm(@RequestBody EmailAuthRequestDto emailDto) {
        Map<String, String> response = new HashMap<>();
        try {
            String authCode = emailService.sendEmail(emailDto.getEmail());
            response.put("status", "success");
            response.put("authCode", authCode);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "메일 전송에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/sendConfirmationCode")
    public ResponseEntity<String> sendConfirmationCode(@RequestBody Map<String, String> requestBody, Principal principal) {
        String newEmailAddress = requestBody.get("newEmailAddress");
        String currentUsername = principal.getName();

        try {
            // 새로운 이메일로 인증 코드 전송
            String confirmationCode = emailService.sendEmail(newEmailAddress);
            String notificationMessage = "새로운 이메일 주소로 인증번호가 전송되었습니다.";
            return ResponseEntity.ok(notificationMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 변경 중 오류가 발생했습니다.");
        }
    }

}
