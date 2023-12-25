package com.lec.spring.controller;

import com.lec.spring.DTO.UserDto;
import com.lec.spring.domain.User;
import com.lec.spring.repository.SmsCertification;
import com.lec.spring.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    private final MessageService messageService;
    private final SmsCertification smsCertification;

    public SmsController(MessageService messageService, SmsCertification smsCertification) {
        this.messageService = messageService;
        this.smsCertification = smsCertification;
    }

    @PostMapping("/user/register/sms")
    public ResponseEntity<?> sendSms(@RequestBody UserDto.SmsCertificationDto smsCertificationDto) {
        try {
            messageService.sendSMS(smsCertificationDto.getPhoneNumber());
            return ResponseEntity.ok("인증번호가 발송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SMS 발송중 문제가 발생했습니다.");
        }
    }

    @PostMapping("/user/register/verify")
    public ResponseEntity<String> verifySms(UserDto.SmsCertificationDto requestDto) {
        boolean isVerfied = smsCertification.isVerificationSuccessful(requestDto.getPhoneNumber(), requestDto.getRandomNumber());


        if (!isVerfied) {
            return ResponseEntity.badRequest().body("인증번호가 일치하지 않습니다.");
        }

        smsCertification.deleteSmsCertification(requestDto.getPhoneNumber());
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }


}
