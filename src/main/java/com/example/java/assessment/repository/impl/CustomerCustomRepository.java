package com.example.java.assessment.repository.impl;

import com.example.java.assessment.entity.Customer;
import reactor.core.publisher.Mono;

public interface CustomerCustomRepository {

    Mono<Customer> insert(Customer customer);
}
