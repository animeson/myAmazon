package com.amazon.svistun.repository.production;

import com.amazon.svistun.entity.production.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @NotNull List<Product> findAll();
    Product getByProductId(Long id);
    List <Product> getProductByCategory_CategoryName(String categoryName);
    List<Product> getProductByBrand_BrandName(String brandName);
    List<Product> getProductByProductName(String productName);
    Boolean existsProductByProductName(String productName);

    void deleteProductByProductId(Long productId);
    Boolean existsProductByCategoryCategoryName(String productName);
    Boolean existsProductByBrandBrandName(String brand_brandName);

}
