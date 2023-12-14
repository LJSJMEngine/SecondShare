package com.lec.spring.controller;


import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {


    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        boolean result = User.class.isAssignableFrom(clazz);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        String username = user.getUsername();
        if (username == null || username.trim().isEmpty()) {
            errors.rejectValue("username", "ID 는 필수 입력 사항입니다.");
        } else if (userService.idExist(username)) {
            errors.rejectValue("username", "이미 존재하는 아이디입니다.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "이름은 필수 입력 사항입니다.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "비밀번호는 필수 입력 사항입니다.");

        // TODO 정규표현식 (비밀번호 + 이메일)

        if (!user.getPassword().equals(user.getPasswordChk())) {
            errors.rejectValue("passwordChk", "입력한 비밀번호와 일치하지 않습니다.");
        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}













