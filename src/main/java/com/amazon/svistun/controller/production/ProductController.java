package com.amazon.svistun.controller.production;

import com.amazon.svistun.dto.production.request.ProductRequestDto;
import com.amazon.svistun.dto.production.response.ProductDto;
import com.amazon.svistun.service.production.productService.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id ) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("category/{categoryName}")
    public ResponseEntity<?> getProductByCategoryName(@PathVariable String categoryName) {
        return ResponseEntity.ok(productService.getProductByCategory_CategoryName(categoryName));
    }

    @GetMapping("brand/{brandName}")
    public ResponseEntity<?> getProductByBrandName(@PathVariable String brandName) {
        return ResponseEntity.ok(productService.getProductByBrand_BrandName(brandName));
    }



    @PostMapping
    public void addProduct(@RequestBody ProductRequestDto product){
        productService.addProduct(product);
    }

    @PatchMapping("edit/{id}")
    public void editProduct(@RequestBody ProductRequestDto product,
                            @PathVariable Long id){
        productService.editProduct(product, id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable Long id){

        productService.deleteProductByProductId(id);
    }
}
