package com.mycompany.Service;

import com.mycompany.Repository.CategoryRepository;
import com.mycompany.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() { return categoryRepository.findAll(); }

    public Optional<Category> getCategoryById(Long id) { return categoryRepository.findById(id); }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDetails.getName());
        if (categoryDetails.getParentCategory() != null) {
            category.setParentCategory(categoryRepository.findById(categoryDetails.getParentCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found")));
        }
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }


}
