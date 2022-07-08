package com.amazon.svistun.controller.production;

import com.amazon.svistun.dto.production.response.BrandDto;
import com.amazon.svistun.entity.production.Brand;
import com.amazon.svistun.service.production.brandService.BrandServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/brand")
public class BranController {
    private final BrandServiceImpl brandService;

    public BranController(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    private List<Brand> getAllBrand() {
        return brandService.getAllBrand();
    }

    @PostMapping
    public void createBrand(@RequestBody BrandDto brandDto) {
        brandService.createBrand(brandDto);
    }

    @PatchMapping("edit/{id}")
    public void editBrand(@RequestBody BrandDto brandDto,
                          @PathVariable Long id) {
        brandService.updateBrandById(id, brandDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteBrandById(@PathVariable Long id) {
        brandService.deleteBrandById(id);
    }

}
