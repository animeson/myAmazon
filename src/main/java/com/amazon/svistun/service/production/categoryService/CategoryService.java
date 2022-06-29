package com.amazon.svistun.service.production.categoryService;

import com.amazon.svistun.dto.production.response.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryDto> getAllCategory();
}
