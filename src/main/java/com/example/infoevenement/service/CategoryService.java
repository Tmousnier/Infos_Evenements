package com.example.infoevenement.service;

import com.example.infoevenement.dao.Category;
import com.example.infoevenement.dto.CategoryInput;
import com.example.infoevenement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category createCategory(CategoryInput categoryInput) {
        Category newcategory = Category.builder().libelle(categoryInput.libelle()).build();
        return categoryRepository.save(newcategory);
    }

    public Category updateCategory(String id, CategoryInput categoryInput) {
        Category category = categoryRepository.findById(id).orElseThrow();

        category.setLibelle(categoryInput.libelle());

        return categoryRepository.save(category);
    }

    public Category deleteCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
        return category;
    }

}
