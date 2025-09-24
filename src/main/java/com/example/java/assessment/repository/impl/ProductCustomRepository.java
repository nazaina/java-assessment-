package com.example.java.assessment.repository.impl;

import com.example.java.assessment.entity.Customer;
import com.example.java.assessment.entity.Product;
import reactor.core.publisher.Mono;

public interface ProductCustomRepository {

    Mono<Product> insert(Product product);
}
