package com.samuell.rhino.controller;

import com.samuell.rhino.model.Category;
import com.samuell.rhino.model.dto.CategoryDto;
import com.samuell.rhino.repository.CategoryRepository;
import com.samuell.rhino.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project/")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/{projectId}/category")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllCategoriesByProjectId(@PathVariable("projectId") Integer projectId) {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategoriesByProjectId(projectId);

        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/category/{categoryId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getCategoryById(@PathVariable("projectId") Integer projectId, @PathVariable("categoryId") Integer categoryId) {
        CategoryDto categoryDto = categoryService.getCategoryById(projectId, categoryId);

        if(categoryDto == null){
            return new ResponseEntity<>("Category not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }
    }

    @PostMapping("/{projectId}/category")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addCategory(@PathVariable("projectId") Integer projectId, @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.addCategory(projectId, categoryDto);

        if(category == null){
            return new ResponseEntity<>("Error while creating category",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Category created with ID: "+ category.getId());
        }
    }

    @PostMapping("/{projectId}/category/{categoryId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateCategory(@PathVariable("projectId") Integer projectId, @PathVariable("categoryId") Integer categoryId, @RequestBody CategoryDto categoryDto) {
        if(categoryService.getCategoryById(projectId, categoryId) == null){
            return new ResponseEntity<>("Category not found",HttpStatus.PRECONDITION_FAILED);
        }
        else {
            Category category = categoryService.updateCategory(projectId, categoryId, categoryDto);

            return ResponseEntity.status(HttpStatus.OK).body("Category with ID: "+ category.getId() + " was updated");
        }
    }

    @DeleteMapping("/{projectId}/category/{categoryId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> deleteCategory(@PathVariable("projectId") Integer projectId,@PathVariable("categoryId") Integer categoryId) {
        if(categoryService.getCategoryById(projectId, categoryId) == null){
            return new ResponseEntity<>("Version not found",HttpStatus.NOT_FOUND);
        }
        else {
            Category category = categoryService.deleteCategory(categoryId);

            return ResponseEntity.status(HttpStatus.OK).body("Category with ID: "+ category.getId() + " was deleted");
        }
    }
}
