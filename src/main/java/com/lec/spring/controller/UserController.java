package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.UserValidator;
import com.lec.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    //test001
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public void login(Model model){}

    @PostMapping("/login")
    public void loginProcess(){}

    @PostMapping("/loginError")
    public String loginError(){
        return "user/login";
    }

    @RequestMapping("/rejectAuth")
    public String rejectAuth(){
        return "common/rejectAuth";
    }

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String registerOk(
            @Valid User user,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
            ){

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("username", user.getUsername());
            redirectAttributes.addFlashAttribute("name", user.getName());
            redirectAttributes.addFlashAttribute("email", user.getEmail());
            redirectAttributes.addFlashAttribute("password", user.getPassword());

            List<FieldError> errorList = result.getFieldErrors();
            for (FieldError error : errorList) {
                redirectAttributes.addFlashAttribute("error", error.getCode());
                break;
            }
            return "redirect:/user/register";
        }

        int count = userService.register(user);
        model.addAttribute("result", count);
        return "/user/registerOk";
    }

    @Autowired
    UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }



}
