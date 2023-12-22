package com.lec.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Value("${app.upload.path}")
    private String uploadDir;   // "upload" 경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 첨부파일 경로 설정
        registry
                .addResourceHandler("/upload/**")
                .addResourceLocations("file:" + uploadDir + "/")
                ;
    }

}
