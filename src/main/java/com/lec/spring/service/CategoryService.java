package com.lec.spring.service;

import com.lec.spring.domain.Category;
import com.lec.spring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {



    List<Category> getAllCategories();
    Category getCategoryByName(String name);
}
