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
                        .requestMatchers("/scdch/detail/**").authenticated()
                        .requestMatchers(
                                "/scdsh/write/**",
                                "/scdsh/update/**",
                                "/scdsh/delete/**").hasAnyRole("ADMIN", "MEMBER")
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/scdsh/login")
                        .loginProcessingUrl("/scdsh/login")
                        .defaultSuccessUrl("/")

                        .successHandler(new CustomLoginSuccessHandler("/main"))
                        .failureHandler(new CustomLoginFailureHandler())
                )

                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/scdsh/logout")
                        .logoutSuccessUrl("/scdsh/login?logout")
                        .invalidateHttpSession(false)

                        .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                )

                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                )

                .build();

    }



}
