package com.amazon.svistun.service.production.categoryService;

import com.amazon.svistun.dto.production.response.CategoryDto;
import com.amazon.svistun.entity.production.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAllCategory();
    void addCategory(CategoryDto categoryDto);
    void deleteCategoryById(Long id);
    void editCategoryById(Long id, CategoryDto categoryDto);


}
