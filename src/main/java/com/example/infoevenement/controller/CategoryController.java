package com.example.infoevenement.controller;

import com.example.infoevenement.dao.Category;
import com.example.infoevenement.dto.CategoryInput;
import com.example.infoevenement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoriesById(@PathVariable String id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody CategoryInput categoryInput) {
        return categoryService.createCategory(categoryInput);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable String id, @RequestBody CategoryInput categoryInput) {
        return categoryService.updateCategory(id, categoryInput);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        categoryService.deleteCategory(id);
    }
}
