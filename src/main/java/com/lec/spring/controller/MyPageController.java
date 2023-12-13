package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    private final UserService userService;

    @Autowired
    public MyPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String showMyPage(Model model) {
        // 현재 로그인한 사용자의 정보 가져오기
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String username = auth.getName();
        // model.addAttribute("userProfile", userService.getUserByUsername(username));
        String currentUsername = "USER1";
        User userProfile = userService.getUserByUsername(currentUsername);
        model.addAttribute("userProfile", userProfile);

        return "mypage/home";
    }

    @GetMapping("/view")
    public String showViewPage(Model model) {
        String currentUsername = "USER1";
        User userProfile = userService.getUserByUsername(currentUsername);
        model.addAttribute("userProfile", userProfile);

        return "mypage/view";
    }

    @PostMapping("/deleteAccount")
    public ResponseEntity<String> deleteAccount() {
        String currentUsername = "USER1"; // 실제 사용자 아이디를 가져오도록 수정

        try {
            userService.deleteAccount(currentUsername);
            return ResponseEntity.ok("회원 탈퇴가 성공적으로 이루어졌습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/modify")
    public String showModifyPage(Model model) {
        String currentUsername = "USER1";
        User userProfile = userService.getUserByUsername(currentUsername);
        model.addAttribute("userProfile", userProfile);

        return "mypage/modify";
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> requestBody) {
        String newPassword = requestBody.get("newPassword");
        String currentUsername = "USER1";  // 여기서 실제 사용자 아이디를 가져와야 함

        try {
            userService.updatePassword(newPassword, currentUsername);
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호 변경 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/updatePhoneNumber")
    public ResponseEntity<String> updatePhoneNumber(@RequestBody Map<String, String> requestBody) {
        String newPhoneNumber = requestBody.get("newPhoneNumber");
        String currentUsername = "USER1";  // 여기서 실제 사용자 아이디를 가져와야 함

        try {
            userService.updatePhoneNumber(newPhoneNumber, currentUsername);
            return ResponseEntity.ok("핸드폰 번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("핸드폰 번호 변경 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/updateEmailAddress")
    public ResponseEntity<String> updateEmailAddress(@RequestBody Map<String, String> requestBody) {
        String newEmailAddress = requestBody.get("newEmailAddress");
        String currentUsername = "USER1";  // 여기서 실제 사용자 아이디를 가져와야 함

        try {
            userService.updateEmailAddress(newEmailAddress, currentUsername);
            return ResponseEntity.ok("이메일이 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 변경 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        return "mypage/products";
    }
}