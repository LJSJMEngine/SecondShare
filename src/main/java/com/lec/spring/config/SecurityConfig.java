package com.lec.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        System.out.println("PasswordEncoder bean 생성");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().anyRequest();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/board/detail/**").authenticated()
                        .requestMatchers("/board/write/**", "/board/modify/**", "/board/delete/**", "/mypage/**", "/user/userpage/**").hasAnyRole("MEMBER", "ADMIN")
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/")

                        .successHandler(new CustomLoginSuccessHandler("/main"))
                        .failureHandler(new CustomLoginFailureHandler())
                )

                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/login?logout")
                        .invalidateHttpSession(false)

                        .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                )

                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                )

                .build();

    }

}
