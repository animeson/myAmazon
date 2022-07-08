package com.amazon.svistun.controller.production;

import com.amazon.svistun.dto.production.response.CategoryDto;
import com.amazon.svistun.service.production.categoryService.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }


    @GetMapping("/{categoryName}")
    public ResponseEntity<?> getCategoryByCategoryName (@PathVariable String categoryName){
        return ResponseEntity.ok().body(categoryService.getCategoryByCategoryName(categoryName));
    }

    @PostMapping
    public void addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.addCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @PatchMapping("/{id}")
    public void editCategoryById(@PathVariable Long id,
                                 @RequestBody CategoryDto categoryDto) {

        categoryService.editCategoryById(id,categoryDto);
    }

}
