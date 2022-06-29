package com.amazon.svistun.service.production.categoryService;

import com.amazon.svistun.dto.production.response.CategoryDto;
import com.amazon.svistun.entity.production.Category;
import com.amazon.svistun.repository.production.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (Category fromCategories : categories) {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(fromCategories, categoryDto);
            categoryDtoList.add(categoryDto);
        }

        return categoryDtoList;
    }
}
