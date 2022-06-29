package com.amazon.svistun.controller.production;

import com.amazon.svistun.service.production.categoryService.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
}
