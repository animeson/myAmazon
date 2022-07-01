package com.amazon.svistun.repository.production;

import com.amazon.svistun.entity.production.Brand;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand getByBrandName(String brandName);
    Brand getBrandByBrandId(Long brandId);
    @NotNull List<Brand> findAll();
    void deleteBrandByBrandId(Long brandId);
    Boolean existsBrandByBrandName(String brandName);




}
