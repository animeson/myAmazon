package com.amazon.svistun.dto.production.request;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String productName;
    private String brandName;
    private String  categoryName;
    private Integer modelYear;
    private Double listPrice;
}
