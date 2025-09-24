package com.example.java.assessment.controller;

import com.example.java.assessment.config.BaseResponse;


import com.example.java.assessment.dto.ProductDto;
import com.example.java.assessment.dto.ProductResponseDto;


import com.example.java.assessment.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Create a new product",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponseDto.class)
                            )
                    )
            }
    )
    @PostMapping
    public Mono<ResponseEntity<BaseResponse<Object>>> addProduct(@RequestBody ProductDto product) {
        return productService.create(product)
                .map(data -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(HttpStatus.CREATED.value());
                    baseResponse.setMessage("Product created successfully");
                    baseResponse.setData(data);
                    return ResponseEntity.ok(baseResponse);
                })
                .onErrorResume(throwable -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(2000);
                    baseResponse.setData(throwable.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body(baseResponse));
                });

    }

    @Operation(
            summary = "Get all list product",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping
    public Mono<ResponseEntity<BaseResponse<Object>>> getAllProducts() {
        return productService.getAll()
                .collectList()
                .map(products -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(HttpStatus.OK.value());
                    baseResponse.setMessage("Product list retrieved successfully");
                    baseResponse.setData(products);

                    return ResponseEntity.ok(baseResponse);
                })
                .onErrorResume(throwable -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(2000);
                    baseResponse.setMessage(throwable.getMessage());
                    baseResponse.setData(null);

                    return Mono.just(ResponseEntity.internalServerError().body(baseResponse));
                });
    }

    @Operation(
            summary = "Get product by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public Mono<ResponseEntity<BaseResponse<Object>>> getProductById(@PathVariable UUID id) {
        return productService.findById(id) // returns Mono<Customer>
                .map(product -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(HttpStatus.OK.value());
                    baseResponse.setMessage("Product retrieved successfully");
                    baseResponse.setData(product);
                    return ResponseEntity.ok(baseResponse);
                })
                .onErrorResume(throwable -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(2000);
                    baseResponse.setData(throwable.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body(baseResponse));
                });
    }

    @Operation(
            summary = "Update product by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponseDto.class)
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public Mono<ResponseEntity<BaseResponse<Object>>> updateProduct (@PathVariable UUID id, @RequestBody ProductDto product) {

        return productService.update(id,product)
                .map(data -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(HttpStatus.OK.value());
                    baseResponse.setMessage("Product updated successfully");
                    baseResponse.setData(data);
                    return ResponseEntity.ok(baseResponse);
                })
                .onErrorResume(throwable -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(2000);
                    baseResponse.setData(throwable.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body(baseResponse));
                });

    }

    @Operation(
            summary = "Delete product by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product deleted successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponseDto.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<BaseResponse<Object>>> deleteProduct(@PathVariable UUID id) {

        return productService.delete(id)
                .then(Mono.defer(() -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(HttpStatus.OK.value());
                    baseResponse.setMessage("Product deleted successfully");
                    baseResponse.setData(null);
                    return Mono.just(ResponseEntity.ok(baseResponse));
                }))
                .onErrorResume(throwable -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(2000);
                    baseResponse.setMessage(throwable.getMessage());
                    baseResponse.setData(null);
                    return Mono.just(ResponseEntity.badRequest().body(baseResponse));
                });


    }
}
