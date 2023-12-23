package com.lec.spring.service;

import com.lec.spring.DTO.UserDto;
import com.lec.spring.repository.SmsCertification;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;


@Service
//@RequiredArgsConstructor
public class MessageService {

    private final SmsCertification smsCertification;

    public MessageService(SmsCertification smsCertification) {
        this.smsCertification = smsCertification;
    }

    @Value("${coolsms.api.key}")
    private String apiKey;
    @Value("${coolsms.api.secret}")
    private String apiSecretKey;

    @Value("${coolsms.api.fromnumber}")
    private String fromNumber;


    private String createRandomNum() {
        Random ran = new Random();
        String verifyNum = "";
        for (int i = 0; i < 4; i++) {
            String random = Integer.toString(ran.nextInt(10));
            verifyNum += random;
        }
        return verifyNum;
    }

    private HashMap<String, String> makeParams(String to, String randomNum) {
        HashMap<String, String> params = new HashMap<>();
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("app_version", "test app 1.2");
        params.put("to", to);
        params.put("text", randomNum);

        return params;
    }


    public String sendSMS(String phoneNum) {

        Message coolsms = new Message(apiKey, apiSecretKey);

        String randomNumber = createRandomNum();
        String messageContent = "인증번호는 " + randomNumber + "입니다.";

        HashMap<String, String> params = makeParams(phoneNum, messageContent);

        try {
            JSONObject obj = new JSONObject(coolsms.send(params));
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        smsCertification.createSmsCertification(phoneNum, randomNumber);

        return "문자가 전송되었습니다.";
    }


    // 인증 완료 검증
    public boolean isVerificationSuccessful(UserDto.SmsCertificationDto requestDto) {
        return smsCertification.hasKey(requestDto.getPhoneNumber()) &&
                smsCertification.getSmsCertification(requestDto.getPhoneNumber())
                        .equals(requestDto.getRandomNumber());
    }


}


