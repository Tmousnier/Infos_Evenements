package com.example.infoevenement.controller;

import com.example.infoevenement.dao.Category;
import com.example.infoevenement.dto.CategoryInput;
import com.example.infoevenement.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping
    public Category getCategoriesById(@PathVariable String id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody CategoryInput categoryInput) {
        return categoryService.createCategory(categoryInput);
    }

    @PutMapping
    public Category updateCategory(@PathVariable String id, @RequestBody CategoryInput categoryInput) {
        return categoryService.updateCategory(id, categoryInput);
    }
}
