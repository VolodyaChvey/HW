package com.chvey.service;

import com.chvey.domain.Category;
import com.chvey.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }
    public Category getCategoryById(Long id){
        return categoryRepository.findCategoryById(id);
    }
}
