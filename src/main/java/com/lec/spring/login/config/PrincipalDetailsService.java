package com.lec.spring.login.config;

import com.lec.spring.domain.User;
import com.lec.spring.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userService.findByUsername(username);


        // DB 에 해당 username 이 존재할 때
        if (user != null) {
            PrincipalDetails userDetails = new PrincipalDetails(user);
            userDetails.setUserService(userService);
            return userDetails;
        }

        // DB 에 해당 username이 존재하지 않을 때
        throw new UsernameNotFoundException(username);

    }
}
