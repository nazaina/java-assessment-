package com.example.java.assessment.service;

import com.example.java.assessment.config.ResourceNotFoundException;
import com.example.java.assessment.dto.ProductDto;
import com.example.java.assessment.entity.Product;
import com.example.java.assessment.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> create(ProductDto request){
        Product product = new Product();
        product.setBookTitle(request.getBookTitle());
        product.setBookPrice(request.getBookPrice());
        product.setBookQuantity(request.getBookQuantity());
        return productRepository.insert(product);
    }

    public Mono<Product> update(UUID id, ProductDto c){

        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product not found")))
                .flatMap(exist -> {
                    exist.setBookTitle(c.getBookTitle());
                    exist.setBookPrice(c.getBookPrice());
                    exist.setBookQuantity(c.getBookQuantity());
                    return productRepository.save(exist);
                });
    }

    public Flux<Product> getAll(){
        return productRepository.findAll();
    }

    public Mono<Product> findById(UUID id){
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product not found")));
    }

    public Mono<Void> delete(UUID id){
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product not found")))
                .flatMap(productRepository::delete);
    }

}
