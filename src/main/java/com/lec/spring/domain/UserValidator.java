package com.lec.spring.domain;

import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

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

        // ID 검증
        String username = user.getUsername();
        if (username == null || username.trim().isEmpty()) {
            errors.rejectValue("username", "ID 는 필수 입력 사항입니다.");
        } else if (userService.idExist(username)) {
            errors.rejectValue("username", "이미 사용중인 ID 입니다.");
        }


        // 비밀번호 검증
        String pw = user.getPassword();
        boolean result1 = Pattern.matches("^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{8,22}+$", pw);
        if (result1 != true) {
            errors.rejectValue("password", "비밀번호 형식에 맞지 않습니다.");
        }
        if (!user.getPassword().equals(user.getPasswordChk())) {
            errors.rejectValue("passwordChk", "입력한 비밀번호와 일치하지 않습니다.");
        }


        // 이메일 검증
        String em = user.getEmail();
        boolean result2 = Pattern.matches("^[a-zA-Z0-9]+@[0-9a-zA-Z]+\\.[a-z]+$", em);
        if (result2 != true) {
            errors.rejectValue("email", "이메일 형식에 맞지 않습니다.");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "이름은 필수 입력 사항입니다.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "비밀번호는 필수 입력 사항입니다.");
    }

}

