package com.lec.spring.util;

import com.lec.spring.login.config.PrincipalDetails;
import com.lec.spring.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class U {
    //현재 request
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attr =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }

    //현재 ssesion
    public static HttpSession getSession(){
        return getRequest().getSession();
    }

    // 로그인 한 사용자 user 구하기
    public static User getLoggedUser(){
        // 현재 로그인 한 사용자
        PrincipalDetails userDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        return user;
    }

    // 첨부파일 정보 출력하기
    public static void printFileInfo(MultipartFile file) {
        String originalFileName = file.getOriginalFilename(); // 원본 파일 이름
        if (originalFileName == null || originalFileName.length() == 0) {
            System.out.println("\t파일이 존재하지 않습니다!");
            return;
        }

        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(file.getInputStream());

            if (bufferedImage != null){
                System.out.printf("\t이미지 파일입니다: %d X $d\n", bufferedImage.getWidth(), bufferedImage.getHeight());
            } else {
                System.out.println("\t이미지 파일이 아닙니다.");
            }
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

}