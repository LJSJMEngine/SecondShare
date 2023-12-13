package com.lec.spring.controller;

import com.lec.spring.repository.SmsCertification;
import jakarta.annotation.PostConstruct;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Random;


public class SmsUtil {

//    TODO 초기화 관련 문제 발생 (해결 필요)
//    private final SmsCertification smsCertification;

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
        params.put("to", to);
        params.put("text", randomNum);

        return params;
    }


    public String sendSMS(String phoneNum) {
        Message coolsms = new Message(apiKey, apiSecretKey);

        String randomNum = createRandomNum();
        System.out.println(randomNum);

        HashMap<String, String> params = makeParams(phoneNum, randomNum);

//        try {
//            JSONObject obj = (JSONObject) coolsms.send(params);
//            System.out.println(obj.toString());
//        } catch (CoolsmsException e) {
//            System.out.println(e.getMessage());
//            System.out.println(e.getCode());
//        }

//        smsCertification.createSmsCertification(phoneNum, randomNum);

        return "문자가 전송되었습니다.";
    }



    // TODO 인증 번호 검증












}



