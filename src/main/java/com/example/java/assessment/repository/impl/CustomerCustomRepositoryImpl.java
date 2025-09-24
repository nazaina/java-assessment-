package com.example.java.assessment.repository.impl;

import com.example.java.assessment.entity.Customer;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class CustomerCustomRepositoryImpl implements CustomerCustomRepository{

    private final R2dbcEntityTemplate template;

    public CustomerCustomRepositoryImpl(R2dbcEntityTemplate template) {
        this.template = template;
    }

    @Override
    public Mono<Customer> insert(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(UUID.randomUUID());
        }
        return template.insert(Customer.class).using(customer); // ✅ pure insert
    }
}
