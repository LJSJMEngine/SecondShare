package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String repassword;
    private String name;
    private String phoneNM;
    private String email;
    private LocalDateTime registDate;
    private String birth;
    private int status;

    // 회원 권한
//    private List<Authority> authorities = new ArrayList<>();
}
