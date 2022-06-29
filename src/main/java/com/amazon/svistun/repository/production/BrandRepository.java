package com.amazon.svistun.repository.production;

import com.amazon.svistun.entity.production.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand getByBrandName(String brandName);



}
