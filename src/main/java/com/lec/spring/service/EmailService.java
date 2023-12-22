package com.lec.spring.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailService {
    private static final Map<String, String> confirmationCodeMap = new ConcurrentHashMap<>();
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private String authNum; // 랜덤 인증 코드

    // 생성자를 통해 authNum 초기화
    public EmailService(JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        createCode(); // authNum 초기화
    }

    // 랜덤 인증 코드 생성
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 :
                    key.append((char) (int)random.nextInt(26) + 97);
                    break;
                case 1 :
                    key.append((char) (int)random.nextInt(26) + 65);
                    break;
                case 2 :
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authNum = key.toString();
    }

    // 메일 양식 작성
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {
        String setFrom = "devsmko@naver.com"; // email-config 에 설정된 이메일 주소
        String toEmail = email; // 받는 사람
        String title = "이메일 변경 확인 인증 번호"; // 제목

        // 인증 코드 생성 및 맵에 저장
        String confirmationCode = generateConfirmationCode(email);

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); // 보낼 이메일 설정
        message.setSubject(title); // 제목 설정
        message.setFrom(setFrom); // 보내는 이메일
        message.setText(setContext(confirmationCode), "utf-8", "html");

        return message;
    }

    //실제 메일 전송
    public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {

        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(toEmail);
        //실제 메일 전송
        emailSender.send(emailForm);

        return authNum; //인증 코드 반환
    }


    // Thymeleaf 이용한 context 설정
    public String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("mailConfirm", context); // mailConfirm.html
    }

    // 확인 코드 검증
    public boolean verifyConfirmationCode(String newEmailAddress, String confirmationCodeFromUser) {
        // 사용자가 제출한 확인 코드와 저장된 확인 코드 비교
        String storedConfirmationCode = confirmationCodeMap.get(newEmailAddress);
        return Objects.equals(storedConfirmationCode, confirmationCodeFromUser);
    }

    // 확인 코드 생성 (랜덤 문자열 등)
    private String generateConfirmationCode(String newEmailAddress) {
        String confirmationCode = RandomStringUtils.randomAlphanumeric(6);

        // 생성된 코드를 맵에 저장
        confirmationCodeMap.put(newEmailAddress, confirmationCode);

        return confirmationCode;
    }

    // https://bitly.ws/36rcM
    // https://green-bin.tistory.com/83
    // https://bigdown.tistory.com/684

}
