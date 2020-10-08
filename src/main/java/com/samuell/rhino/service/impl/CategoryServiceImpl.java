package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Category;
import com.samuell.rhino.model.dto.CategoryDto;
import com.samuell.rhino.repository.CategoryRepository;
import com.samuell.rhino.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        return null;
    }

    @Override
    public Category addCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public Category updateCategory(Integer id, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public Category deleteCategory(Integer id) {
        return null;
    }
}
