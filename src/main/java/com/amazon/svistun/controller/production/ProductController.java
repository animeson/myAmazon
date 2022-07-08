package com.amazon.svistun.controller.production;

import com.amazon.svistun.dto.production.request.ProductEditDto;
import com.amazon.svistun.dto.production.request.ProductRequestDto;
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

    @GetMapping("/{productName}")
    public ResponseEntity<?> getProductByProductName(@PathVariable String productName ) {
        return ResponseEntity.ok(productService.getProductByProductName(productName));
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
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDto product){
        return ResponseEntity.ok().body(productService.addProduct(product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editProduct(@RequestBody ProductEditDto product,
                                         @PathVariable Long id){
        return ResponseEntity.ok().body(productService.editProduct(product, id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProductByProductId(id);
    }
}
