package com.amazon.svistun.repository.production;

import com.amazon.svistun.entity.production.Category;
import com.amazon.svistun.entity.production.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    Product getByProductId(Long id);
    List <Product> getProductByCategory_CategoryName(String categoryName);
    List<Product> getProductByBrand_BrandName(String brandName);

    void deleteProductByProductId(Long productId);

}
