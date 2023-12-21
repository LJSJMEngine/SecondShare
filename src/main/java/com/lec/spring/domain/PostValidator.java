package com.lec.spring.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PostValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {

        boolean result = Post.class.isAssignableFrom(clazz);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Post post = (Post) target;

        Long category_id = post.getCategory_id();
        if(category_id == 0){
            errors.rejectValue("category_id", "카테고리를 선택해주세요!");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "글 제목은 필수입니다");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price","가격을 입력해주세요!");

    }
}
