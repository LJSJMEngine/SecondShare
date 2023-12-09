package com.lec.spring.repository;

import com.lec.spring.domain.User;

public interface UserRepository {
    int save(User user);

    int update(Long id);
}
