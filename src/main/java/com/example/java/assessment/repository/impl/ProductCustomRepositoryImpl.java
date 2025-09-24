package com.example.java.assessment.repository.impl;

import com.example.java.assessment.entity.Product;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class ProductCustomRepositoryImpl implements ProductCustomRepository{

    private final R2dbcEntityTemplate template;

    public ProductCustomRepositoryImpl(R2dbcEntityTemplate template) {
        this.template = template;
    }

    @Override
    public Mono<Product> insert(Product product) {
        if (product.getId() == null) {
            product.setId(UUID.randomUUID());
        }
        return template.insert(Product.class).using(product);
    }
}
