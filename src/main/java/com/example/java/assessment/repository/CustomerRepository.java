package com.example.java.assessment.repository;

import com.example.java.assessment.entity.Customer;

import com.example.java.assessment.repository.impl.CustomerCustomRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, UUID>, CustomerCustomRepository {
}
