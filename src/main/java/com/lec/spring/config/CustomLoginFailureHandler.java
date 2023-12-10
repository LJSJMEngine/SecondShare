package com.lec.spring.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private final String DEFAULT_FAIL_URL = "/scdsh/loginError";


    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        String errorMsg = null;

        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            errorMsg = "아이디 혹은 비밀번호가 일치하지 않습니다.";
        } else if (exception instanceof DisabledException) {
            errorMsg = "비활성화된 계정입니다.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMsg = "존재하지 않는 계정입니다. 회원가입을 진행해주세요.";
        } else {
            errorMsg = "로그인 실패. 다시 시도하세요.";
        }

        request.setAttribute("errorMsg", errorMsg);
        request.setAttribute("username", request.getParameter("username"));

        request.getRequestDispatcher(DEFAULT_FAIL_URL).forward(request, response);

    }
}
