package com.mycompany.controller;

import com.mycompany.Service.CategoryService;
import com.mycompany.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
   public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category) {
        return new  ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }
}
