package com.lec.spring.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;     // PK
    private String username;    // 아이디
    private String password;    // 비밀번호
    private String passwordChk;     // 비밀번호 확인
    private String name;    // 이름
    private String phoneNM;     // 전화번호
    private String email;   // 이메일
    private LocalDateTime registDate;   // 가입일자
    private String birth;   // 생년월일
    private String status;  // 회원 상태 (활성화 / 비활성화)


    // 회원 권한
    private List<Authority> authorities = new ArrayList<>();
}
