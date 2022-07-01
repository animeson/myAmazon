package com.amazon.svistun.service.production.categoryService;

import com.amazon.svistun.dto.production.response.CategoryDto;
import com.amazon.svistun.entity.production.Category;
import com.amazon.svistun.repository.production.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public void addCategory(@NotNull CategoryDto categoryDto) {
        Category category = new Category();
        if (category.getCategoryName() != null || category.getCategoryName().equals("")) {
            BeanUtils.copyProperties(categoryDto, category);
            categoryRepository.save(category);
            log.info("Category with {} added", categoryDto.getCategoryName());

        } else {
            log.error("The category with {} has not been added. Category name cannot be empty or null",
                    categoryDto.getCategoryName());
        }

    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteCategoriesByCategoryId(id);
            log.info("Category with id = {} deleted", id);
        } else {
            log.error("There is no category with id = {} or it was deleted", id);
        }
    }

    @Override
    public void editCategoryById(Long id, CategoryDto categoryDto) {
        if (categoryRepository.existsById(id)) {
            Category category = categoryRepository.getCategoriesByCategoryId(id);
            category.setCategoryName(categoryDto.getCategoryName());
            categoryRepository.save(category);
            log.info("Category with id = {} edited", id);
        } else {
            log.error("No category with id = {}", id);
        }

    }


}
