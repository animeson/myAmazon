package com.amazon.svistun.repository.production;

import com.amazon.svistun.entity.production.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();
    Category getByCategoryName(String categoryName);

}
