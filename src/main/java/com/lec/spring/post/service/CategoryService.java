package com.lec.spring.post.service;

import com.lec.spring.domain.Category;

import java.util.List;


public interface CategoryService {


    int saveCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryByName(String name);
}
