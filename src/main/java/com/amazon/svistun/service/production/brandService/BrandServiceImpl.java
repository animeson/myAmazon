package com.amazon.svistun.service.production.brandService;

import com.amazon.svistun.dto.production.response.BrandDto;
import com.amazon.svistun.entity.production.Brand;
import com.amazon.svistun.repository.production.BrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandByBrandId(Long brandId) {
        if (!brandRepository.existsById(brandId)) {
            log.error("There is no brand with id = {}, or it has been removed", brandId);
        }
        return brandRepository.getBrandByBrandId(brandId);
    }

    @Override
    @Transactional
    public void deleteBrandById(Long brandId) {
        if (brandRepository.existsById(brandId)) {
            brandRepository.deleteBrandByBrandId(brandId);
        } else {
            log.error("There is no brand with id = {}, or it has been removed", brandId);
        }
    }

    @Override
    @Transactional
    public void createBrand(@NotNull BrandDto brandDto) {
        Brand brand = new Brand();
        if (!brandRepository.existsBrandByBrandName(brandDto.getBrandName())) {
            BeanUtils.copyProperties(brandDto, brand);
            brandRepository.save(brand);
        } else {
            log.error("There is already a brand with the name {} . Think of another name.",
                    brandDto.getBrandName());
        }
    }

    @Override
    @Transactional
    public void updateBrandById(Long brandId, BrandDto brandDto) {
        if (brandRepository.existsById(brandId)) {
            Brand brand = brandRepository.getBrandByBrandId(brandId);
            brand.setBrandName(brandDto.getBrandName());
            brandRepository.save(brand);
        } else {
            log.error("There is no brand with id = {}, or it has been removed", brandId);
        }
    }
}
