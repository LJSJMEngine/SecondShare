package com.lec.spring.controller;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
//import com.lec.spring.domain.UserValidator;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.MemberService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    //test001
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public void login(Model model){}


    @PostMapping("/loginError")
    public String loginError(){
        return "user/login";
    }

    @RequestMapping("/rejectAuth")
    public String rejectAuth(){
        return "user/rejectAuth";
    }

    @GetMapping("/register")
    public void register(){}


    @PostMapping("/register/username")
    @ResponseBody
    public ResponseEntity<Boolean> confirmId(String username) {
        if (username == null || username.trim().isEmpty()) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        boolean result = !memberService.selectUsername(username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/register")
    public String registerOk(User user, Model model) {
        int submit = userService.register(user);
        model.addAttribute("result", submit);
        return "/user/registerOk";
    }

    // 유저 페이지
    @GetMapping("/userpage/{id}")
    public String userpage(@PathVariable Long id ,Model model){
        User user = userService.userpage(id);
        model.addAttribute("user", user);
        userService.findUserPosts(id,model);
        return "user/userpage";
    }


}
