package com.samuell.rhino.service;

import com.samuell.rhino.model.Category;
import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.CategoryDto;
import com.samuell.rhino.model.dto.UserDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CategoryService {
    @Nullable
    List<CategoryDto> getAllCategories();

    @Nullable
    CategoryDto getCategoryById(Integer id);

    @Nullable
    Category addCategory(CategoryDto categoryDto);

    @Nullable
    Category updateCategory(Integer id, CategoryDto categoryDto);

    @Nullable
    Category deleteCategory(Integer id);
}
