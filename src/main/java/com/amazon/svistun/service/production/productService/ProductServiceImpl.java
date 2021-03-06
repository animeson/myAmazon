package com.amazon.svistun.service.production.productService;

import com.amazon.svistun.dto.production.request.ProductEditDto;
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


    public ProductServiceImpl(ProductRepository productRepository,
                              BrandRepository brandRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> product = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product fromProduct : product) {
            productDtoList.add(productBeanUtils(fromProduct, fromProduct.getCategory(),
                    fromProduct.getBrand(),
                    fromProduct.getStocks()));

        }
        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductByCategory_CategoryName(String categoryName) {
        List<Product> product = productRepository.getProductByCategory_CategoryName(categoryName);
        List<ProductDto> productDtoList = new ArrayList<>();
        if (productRepository.existsProductByCategoryCategoryName(categoryName)) {
            for (Product fromProduct : product) {
                productDtoList.add(productBeanUtils(fromProduct,
                        fromProduct.getCategory(),
                        fromProduct.getBrand(),
                        fromProduct.getStocks()));
            }
        } else {
            log.error("There is no product in this {} category or the product has been removed", categoryName);
        }
        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductByBrand_BrandName(String brandName) {
        List<Product> product = productRepository.getProductByBrand_BrandName(brandName);
        List<ProductDto> productDtoList = new ArrayList<>();
        if (productRepository.existsProductByBrandBrandName(brandName)) {
            for (Product fromProduct : product) {
                productDtoList.add(productBeanUtils(fromProduct, fromProduct.getCategory(), fromProduct.getBrand(), fromProduct.getStocks()));
            }
        } else {
            log.error("The {} brand does not exist or the product of this brand has been removed", brandName);
        }

        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductByProductName(String productName) {
        List<Product> product = productRepository.getProductByProductName(productName);
        List<ProductDto> productDtoList = new ArrayList<>();
        if (productRepository.existsProductByProductName(productName)) {
            for (Product fromProduct : product) {
                productDtoList.add(productBeanUtils(fromProduct, fromProduct.getCategory(), fromProduct.getBrand(), fromProduct.getStocks()));
            }
        } else {
            log.error("The {} brand does not exist or the product of this brand has been removed", productName);
        }

        return productDtoList;
    }

    @Override
    @Transactional
    public Product addProduct(@NotNull ProductRequestDto productRequestDto) {
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
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product editProduct(@NotNull ProductEditDto productEditDto, Long editProductId) {
        Product product = productRepository.getByProductId(editProductId);
        if (editProductId != null && categoryRepository.existsById(editProductId)) {

            if (productEditDto.getListPrice() != null && productEditDto.getListPrice() >= 0) {
                product.setListPrice(productEditDto.getListPrice());

            } else {
                log.error("The price of the product cannot be null or less than zero");
            }
        } else {
            log.error("There is no product with id= {} or it was deleted", editProductId);
        }

        return productRepository.save(product);

    }

    @Override
    @Transactional
    public void deleteProductByProductId(@NotNull Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteProductByProductId(id);
            log.info("Product with id = {} deleted", id);
        } else {
            log.error("There is no product with id = {} or it was deleted", id);
        }
    }


    public ProductDto productBeanUtils(@NotNull Product product,
                                       Category category,
                                       Brand brand,
                                       @NotNull List<Stocks> stocks) {
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


