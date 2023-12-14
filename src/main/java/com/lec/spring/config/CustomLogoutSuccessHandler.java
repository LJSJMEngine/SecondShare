package com.lec.spring.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String redirectUrl = "/scdsh/login?logoutHandler";

        if (request.getParameter("return_url") != null) {
            redirectUrl = request.getParameter("return_url");
        }

        response.sendRedirect(redirectUrl);
    }
}
