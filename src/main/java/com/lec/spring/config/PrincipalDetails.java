package com.lec.spring.config;

import com.lec.spring.domain.Authority;
import com.lec.spring.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import com.lec.spring.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrincipalDetails implements UserDetails {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // -------------------------------------------------

    private User user;

    public User getUser() {
        return user;
    }

    public PrincipalDetails(User user) {
        System.out.println("User 생성?");
        this.user = user;
    }


    // -------------------------------------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        List<Authority> list = userService.selectAuthById(user.getId());

        for(Authority authority : list) {
            collection.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return authority.getName();
                }
            });
        }
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}


























