package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private int age;
    private LocalDateTime registDate;
    private String birth;
    private int status;

}
