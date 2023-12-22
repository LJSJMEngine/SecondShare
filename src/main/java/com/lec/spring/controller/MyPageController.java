package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.EmailAuthRequestDto;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.Review;
import com.lec.spring.domain.User;
import com.lec.spring.service.EmailService;
import com.lec.spring.service.PostService;
import com.lec.spring.service.ReviewService;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    private final UserService userService;
    private final ReviewService reviewService;
    @Autowired
    private PostService postService;

    @Autowired
    public MyPageController(UserService userService, ReviewService reviewService, EmailService emailService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.emailService = emailService;
    }

    @GetMapping("/home")
    public String showMyPage(Model model, Principal principal) {
        String currentUsername = principal.getName();
        User userProfile = userService.getUserByUsername(currentUsername);
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("containerRightPage", "/mypage/view");

        return "mypage/home";
    }

    @GetMapping("/view")
    public String showViewPage(Model model, Principal principal) {
        String currentUsername = principal.getName();
        User userProfile = userService.getUserByUsername(currentUsername);
        model.addAttribute("userProfile", userProfile);

        return "mypage/view";
    }

    @PostMapping("/deleteAccount")
    public ResponseEntity<String> deleteAccount(Principal principal) {
        String currentUsername = principal.getName();

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
    public String showModifyPage(Model model, Principal principal) {
        String currentUsername = principal.getName();
        User userProfile = userService.getUserByUsername(currentUsername);
        model.addAttribute("userProfile", userProfile);

        return "mypage/modify";
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> requestBody, Principal principal) {
        String newPassword = requestBody.get("newPassword");
        String currentUsername = principal.getName();

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
    public ResponseEntity<String> updatePhoneNumber(@RequestBody Map<String, String> requestBody, Principal principal) {
        String newPhoneNumber = requestBody.get("newPhoneNumber");
        String currentUsername = principal.getName();

        try {
            userService.updatePhoneNumber(newPhoneNumber, currentUsername);
            return ResponseEntity.ok("핸드폰 번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("핸드폰 번호 변경 중 오류가 발생했습니다.");
        }
    }

    private EmailService emailService;

    @PostMapping("/sendConfirmationCode")
    public ResponseEntity<String> sendConfirmationCode(@RequestBody Map<String, String> requestBody, Principal principal) {
        String newEmailAddress = requestBody.get("newEmailAddress");
        String currentUsername = principal.getName();

        try {
            // 새로운 이메일로 인증 코드 전송
            String confirmationCode = emailService.sendEmail(newEmailAddress);
            String notificationMessage = "새로운 이메일 주소로 인증 코드가 전송되었습니다.";
            return ResponseEntity.ok(notificationMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // 예외 로그 출력
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 변경 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/verifyEmailAddress")
    public ResponseEntity<String> verifyEmailAddress(@RequestBody Map<String, String> requestBody, Principal principal) {
        String confirmationCodeFromUser = requestBody.get("confirmationCode");
        String newEmailAddress = requestBody.get("newEmailAddress");

        String currentUsername = principal.getName();

        try {
            // 사용자가 입력한 확인 코드를 검증
            boolean isCodeValid = emailService.verifyConfirmationCode(newEmailAddress, confirmationCodeFromUser);

            if (isCodeValid) {
                // 확인 코드가 유효하면 이메일 변경
                userService.updateEmailAddress(newEmailAddress, currentUsername);
                return ResponseEntity.ok("이메일이 성공적으로 변경되었습니다.");
            } else {
                // 확인 코드가 일치하지 않으면 오류 응답
                return ResponseEntity.badRequest().body("올바르지 않은 인증 코드 입니다. 다시 작성해 주세요.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 변경 중 오류가 발생했습니다.");
        }
    }


    @GetMapping("/myPosts")
    public String myPosts(Model model, Principal principal) {
        String currentUsername = principal.getName();
        User userProfile = userService.getUserByUsername(currentUsername);
        model.addAttribute("userProfile", userProfile);

        List<Post.MyPosts> myPosts = userService.showMyPosts(userProfile.getId());
        model.addAttribute("myPosts", myPosts);

        long statusOneCount = myPosts.stream().filter(post -> post.getStatus() == 1).count();
        model.addAttribute("statusOneCount", statusOneCount);

        List<Review.MyReceivedReviews> myReceivedReviews = reviewService.findReviewsByUserId(Math.toIntExact(userProfile.getId()));
        model.addAttribute("myReceivedReviews", myReceivedReviews);

        return "mypage/myPosts";
    }

    @PostMapping("/myPostsData")
    public ResponseEntity<List<Post.MyPosts>> showMyPostsData(Principal principal) {
        String currentUsername = principal.getName();
        User user = userService.getUserByUsername(currentUsername);
        Long userId = user.getId();

        List<Post.MyPosts> myPosts = userService.showMyPosts(userId);

        return ResponseEntity.ok(myPosts);
    }

    @PostMapping("/deleteMyPosts")
    public ResponseEntity<String> deleteMyPosts(@RequestBody Map<String, List<Long>> requestBody) {
        List<Long> selectedPostIds = requestBody.get("selectedPostIds");

        if (selectedPostIds != null && !selectedPostIds.isEmpty()) {
            postService.deleteMyPosts(selectedPostIds);
            return ResponseEntity.ok("삭제가 완료되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("삭제할 항목을 선택해주세요.");
        }
    }


}