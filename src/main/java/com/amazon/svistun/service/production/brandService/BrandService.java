package com.amazon.svistun.service.production.brandService;

import com.amazon.svistun.dto.production.response.BrandDto;
import com.amazon.svistun.entity.production.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    List<Brand> getAllBrand();
    Brand getBrandByBrandId(Long brandId);
    void deleteBrandById(Long brandId);
    void createBrand(BrandDto brandDto);
    void updateBrandById(Long brandId, BrandDto brandDto);


}
