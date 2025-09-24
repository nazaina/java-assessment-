package com.example.java.assessment.controller;

import com.example.java.assessment.config.BaseResponse;

import com.example.java.assessment.entity.Product;

import com.example.java.assessment.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Product>> addProduct(@RequestBody Product product) {
        Product productSaved = productService.create(product);
        BaseResponse<Product> response = new BaseResponse<>(
                HttpStatus.CREATED.value(),
                "Product created successfully",
                productSaved
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAll();
        BaseResponse<List<Product>> response = new BaseResponse<>(
                HttpStatus.OK.value(),
                "Product retrieved successfully",
                products
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Product>> getProductById(@PathVariable UUID id) {
        Product product = productService.findById(id);
        BaseResponse<Product> response = new BaseResponse<>(
                HttpStatus.CREATED.value(),
                "Product retrieved successfully",
                product
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Product>> updateProduct (@PathVariable UUID id, @RequestBody Product product) {

        Product updated = productService.update(id, product);

        BaseResponse<Product> response = new BaseResponse<>(
                HttpStatus.CREATED.value(),
                "Product updated successfully",
                updated
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Product>> deleteProduct(@PathVariable UUID id) {

         productService.delete(id);

        BaseResponse<Product> response = new BaseResponse<>(
                HttpStatus.OK.value(),
                "Product deleted successfully",
                null
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
