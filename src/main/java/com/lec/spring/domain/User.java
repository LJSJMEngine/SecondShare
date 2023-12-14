package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;     // PK
    private String username;    // 아이디
    private String password;    // 비밀번호
    @ToString.Exclude
    private String passwordChk;     // 비밀번호 확인
    private String name;    // 이름
    private String phoneNM;     // 전화번호
    private String email;   // 이메일
    private LocalDateTime registDate;   // 가입일자
    private String birth;   // 생년월일
    private int status;  // 회원 상태 (활성화 / 비활성화)


    // 회원 권한
    @Builder.Default
    @ToString.Exclude
    private List<Authority> authorities = new ArrayList<>();

    // 마이페이지 - 프로필 수정
    private String newPassword;
    private String newPhoneNumber;
    private String newEmailAddress;
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }
    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }
    public String getNewEmailAddress() {
        return newEmailAddress;
    }
    public void setNewEmailAddress(String newEmailAddress) {
        this.newEmailAddress = newEmailAddress;
    }

    // 마이페이지 - 판매물품
    @Builder.Default
    @JsonIgnore
    private List<Post.MyPosts> myPosts = new ArrayList<>();
    public List<Post.MyPosts> getMyPosts() {
        return myPosts;
    }
    public void setMyPosts(List<Post.MyPosts> myPosts) {
        this.myPosts = myPosts;
    }
}
