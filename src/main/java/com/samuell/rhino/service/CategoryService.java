package com.samuell.rhino.service;

import com.samuell.rhino.model.Category;
import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.CategoryDto;
import com.samuell.rhino.model.dto.UserDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CategoryService {
    @Nullable
    List<CategoryDto> getAllCategoriesByProjectId(Integer projectId);

    @Nullable
    CategoryDto getCategoryById(Integer projectId, Integer categoryId);

    @Nullable
    Category addCategory(Integer projectId, CategoryDto categoryDto);

    @Nullable
    Category updateCategory(Integer projectId, Integer categoryId, CategoryDto categoryDto);

    @Nullable
    Category deleteCategory(Integer categoryId);
}
