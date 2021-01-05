package com.samuell.rhino.service.impl;

import com.samuell.rhino.controller.form_validation.ValidationHelpers;
import com.samuell.rhino.model.Category;
import com.samuell.rhino.model.dto.CategoryDto;
import com.samuell.rhino.model.mapper.CategoryMapper;
import com.samuell.rhino.repository.CategoryRepository;
import com.samuell.rhino.repository.ProjectRepository;
import com.samuell.rhino.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAllCategoriesByProjectId(Integer projectId) {
        return categoryRepository.findAll()
                .stream()
                .filter(category ->
                        !category.isWas_deleted() &&
                        category.getProject().getId().equals(projectId))
                .map(category -> categoryMapper.toCategoryDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Integer projectId, Integer categoryId) {
        return categoryMapper.toCategoryDto(categoryRepository.findById(categoryId)
                .filter(project -> project.getProject().getId().equals(projectId))
                .orElse(null));
    }

    @Override
    public Category addCategory(Integer projectId, CategoryDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);

        category.setProject(projectRepository.findById(projectId).orElse(null));
        category.setCreated_at(Timestamp.from(Instant.now()));

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Integer projectId, Integer categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElse(new Category());

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setProject(projectRepository.findById(projectId).orElse(null));
        category.setEdited_at(Timestamp.from(Instant.now()));

        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(new Category());
        category.setWas_deleted(true);

        return categoryRepository.save(category);
    }

    @Override
    public Map<String, String> validateCategory(CategoryDto categoryDto) {
        Map<String,String> errors = new HashMap<>();

        if(categoryDto.getName() == null || !categoryDto.getName().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("name","Zadajte názov");

        return errors;
    }
}
