package com.amazon.svistun.dto.production.response;

import lombok.Data;

@Data
public class ProductDto {
    private Long productId;
    private String productName;
    private BrandDto brand;
    private CategoryDto category;
    private StocksDto stocks;
    private Integer modelYear;
    private Double listPrice;
}
