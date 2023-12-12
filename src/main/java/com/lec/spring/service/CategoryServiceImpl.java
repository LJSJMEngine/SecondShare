package com.lec.spring.service;

import com.lec.spring.domain.Category;
import com.lec.spring.repository.CategoryRepository;
import com.lec.spring.repository.PostRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(SqlSession sqlSession) {
        categoryRepository = sqlSession.getMapper(CategoryRepository.class);
        System.out.println("categoryService() 생성");
    }


        @Override
        public List<Category> getAllCategories () {
            return categoryRepository.findAll();
        }

        @Override
        public Category getCategoryByName (String name){
            return categoryRepository.findByName(name);
        }
    }

