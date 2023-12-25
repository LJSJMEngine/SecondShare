package com.lec.spring.config;

import com.lec.spring.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public CustomLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        // user status == 0인 경우에만 로그인 허용
        if (user.getStatus() == 0) {

            List<String> role = new ArrayList<>();
            authentication.getAuthorities().forEach(authority -> {
                role.add(authority.getAuthority());
            });

            super.onAuthenticationSuccess(request, response, authentication);

            HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request);
            SavedRequest savedRequest = (SavedRequest) wrapper.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            System.out.println("Saved Request: " + (savedRequest != null ? savedRequest.getRedirectUrl() : "None"));

        } else {
            // user status == 0이 아닌 경우에는 로그인을 허용하지 않음
            System.out.println("탈퇴한 사용자입니다.");
            // 로그인 페이지로 리다이렉션
            response.sendRedirect("/user/login");
        }
    }

    // client ip 불러오기
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
