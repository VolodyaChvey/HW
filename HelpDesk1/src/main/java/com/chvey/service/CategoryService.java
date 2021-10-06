package com.chvey.service;

import com.chvey.domain.Category;
import com.chvey.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> getCategoryById(Long id){
        return Optional.ofNullable(categoryRepository.findCategoryById(id));
    }
}
