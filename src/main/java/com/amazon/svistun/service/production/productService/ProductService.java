package com.amazon.svistun.service.production.productService;

import com.amazon.svistun.dto.production.request.ProductEditDto;
import com.amazon.svistun.dto.production.request.ProductRequestDto;
import com.amazon.svistun.dto.production.response.ProductDto;
import com.amazon.svistun.entity.production.Brand;
import com.amazon.svistun.entity.production.Category;
import com.amazon.svistun.entity.production.Product;
import com.amazon.svistun.entity.production.Stocks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> getAllProduct();
    ProductDto productBeanUtils(Product product, Category category, Brand brand, List<Stocks> stocks);

    List<ProductDto> getProductByCategory_CategoryName(String categoryName);

    List<ProductDto> getProductByBrand_BrandName(String brandName);
    List<ProductDto> getProductByProductName(String productName);

    Product addProduct(ProductRequestDto productDto);

    Product editProduct(ProductEditDto productEditDto, Long editProductId);
    void deleteProductByProductId(Long id);


}
