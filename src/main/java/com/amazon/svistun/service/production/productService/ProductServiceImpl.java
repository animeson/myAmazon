package com.amazon.svistun.service.production.productService;

import com.amazon.svistun.dto.production.request.ProductRequestDto;
import com.amazon.svistun.dto.production.response.BrandDto;
import com.amazon.svistun.dto.production.response.CategoryDto;
import com.amazon.svistun.dto.production.response.ProductDto;
import com.amazon.svistun.dto.production.response.StocksDto;
import com.amazon.svistun.entity.production.Brand;
import com.amazon.svistun.entity.production.Category;
import com.amazon.svistun.entity.production.Product;
import com.amazon.svistun.entity.production.Stocks;
import com.amazon.svistun.repository.production.BrandRepository;
import com.amazon.svistun.repository.production.CategoryRepository;
import com.amazon.svistun.repository.production.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;


    public ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> product = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product fromProduct : product) {
            productDtoList.add(productBeanUtils(fromProduct, fromProduct.getCategory(), fromProduct.getBrand(), fromProduct.getStocks()));

        }
        return productDtoList;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.getByProductId(id);
        return productBeanUtils(product, product.getCategory(), product.getBrand(), product.getStocks());
    }


    @Override
    public List<ProductDto> getProductByCategory_CategoryName(String categoryName) {
        List<Product> product = productRepository.getProductByCategory_CategoryName(categoryName);
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product fromProduct : product) {
            productDtoList.add(productBeanUtils(fromProduct, fromProduct.getCategory(), fromProduct.getBrand(), fromProduct.getStocks()));
        }
        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductByBrand_BrandName(String brandName) {
        List<Product> product = productRepository.getProductByBrand_BrandName(brandName);
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product fromProduct : product) {
            productDtoList.add(productBeanUtils(fromProduct, fromProduct.getCategory(), fromProduct.getBrand(), fromProduct.getStocks()));
        }
        return productDtoList;
    }

    @Override
    @Transactional
    public void addProduct(@NotNull ProductRequestDto productRequestDto) {
        Product product = new Product();
        Brand brand = new Brand();
        Category category = new Category();
        BeanUtils.copyProperties(productRequestDto, product);

        if (brandRepository.getByBrandName(productRequestDto.getBrandName()) == null) {
            brand.setBrandName(productRequestDto.getBrandName());
            brandRepository.save(brand);
        } else {
            brand = brandRepository.getByBrandName(productRequestDto.getBrandName());
        }

        if (categoryRepository.getByCategoryName(productRequestDto.getCategoryName()) == null) {
            category.setCategoryName(productRequestDto.getCategoryName());
            categoryRepository.save(category);
        } else {
            category = categoryRepository.getByCategoryName(productRequestDto.getCategoryName());
        }

        product.setCategory(category);
        product.setBrand(brand);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void editProduct(@NotNull ProductRequestDto productRequestDto, Long editProductId) {
        Product product = productRepository.getByProductId(editProductId);

        Brand brand = new Brand();
        Category category = new Category();

        if (brandRepository.getByBrandName(productRequestDto.getBrandName()) == null) {
            brand.setBrandName(productRequestDto.getBrandName());
            brandRepository.save(brand);
        } else {
            brand = brandRepository.getByBrandName(productRequestDto.getBrandName());
        }

        if (categoryRepository.getByCategoryName(productRequestDto.getCategoryName()) == null) {
            category.setCategoryName(productRequestDto.getCategoryName());
            categoryRepository.save(category);
        } else {
            category = categoryRepository.getByCategoryName(productRequestDto.getCategoryName());
        }


        product.setBrand(brand);
        product.setCategory(category);

        BeanUtils.copyProperties(productRequestDto, product);

        productRepository.save(product);

    }

    @Override
    @Transactional
    public void deleteProductByProductId(Long id) {
        productRepository.deleteProductByProductId(id);
    }


    public ProductDto productBeanUtils(@NotNull Product product, Category category, Brand brand, @NotNull List<Stocks> stocks) {
        ProductDto productDto = new ProductDto();
        BrandDto brandDto = new BrandDto();
        CategoryDto categoryDto = new CategoryDto();
        StocksDto stocksDto = new StocksDto();

        BeanUtils.copyProperties(category, categoryDto);
        BeanUtils.copyProperties(brand, brandDto);
        BeanUtils.copyProperties(product, productDto);


        for (Stocks stock : stocks) {
            BeanUtils.copyProperties(stock, stocksDto);
        }

        productDto.setBrand(brandDto);
        productDto.setCategory(categoryDto);
        productDto.setStocks(stocksDto);

        return productDto;
    }


}


