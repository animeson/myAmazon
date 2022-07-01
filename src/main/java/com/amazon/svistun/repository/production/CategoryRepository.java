package com.amazon.svistun.repository.production;

import com.amazon.svistun.entity.production.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @NotNull List<Category> findAll();

    Category getByCategoryName(String categoryName);
    Category getCategoriesByCategoryId(Long categoryId);

    void deleteCategoriesByCategoryId(Long id);
}
