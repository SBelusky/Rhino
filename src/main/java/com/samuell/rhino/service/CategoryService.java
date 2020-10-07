package com.samuell.rhino.service;

import com.samuell.rhino.model.Category;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CategoryService {
    @Nullable
    List<Category> getAllCategories();

    @Nullable
    Category getCategoryById(int id);

    @Nullable
    Category addCategory(Category category);
}
