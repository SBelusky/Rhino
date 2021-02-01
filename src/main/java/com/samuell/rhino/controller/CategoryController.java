package com.samuell.rhino.controller;

import com.samuell.rhino.model.Category;
import com.samuell.rhino.model.dto.CategoryDto;
import com.samuell.rhino.repository.CategoryRepository;
import com.samuell.rhino.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/project/{projectId}")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllCategoriesByProjectId(@PathVariable("projectId") Integer projectId) {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategoriesByProjectId(projectId);

        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/detail/category/{categoryId}")
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

    @PostMapping("/add/category")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addCategory(@PathVariable("projectId") Integer projectId, @RequestBody CategoryDto categoryDto) {
        Map<String,String> errors = categoryService.validateCategory(categoryDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            Category category = categoryService.addCategory(projectId, categoryDto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Category created with ID: "+ category.getId());
        }
    }

    @PostMapping("/edit/category/{categoryId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateCategory(@PathVariable("projectId") Integer projectId, @PathVariable("categoryId") Integer categoryId, @RequestBody CategoryDto categoryDto) {
        Map<String,String> errors = categoryService.validateCategory(categoryDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            Category category = categoryService.updateCategory(projectId, categoryId, categoryDto);

            return ResponseEntity.status(HttpStatus.OK).body("Category with ID: "+ category.getId() + " was updated");
        }
    }

    @DeleteMapping("/delete/category/{categoryId}")
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
