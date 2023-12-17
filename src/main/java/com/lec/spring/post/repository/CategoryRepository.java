package com.lec.spring.post.repository;

import com.lec.spring.domain.Category;

import java.util.List;

public interface CategoryRepository {
    int save(Category category);
    List<Category> findAll();
    Category findByName(String name);
}
