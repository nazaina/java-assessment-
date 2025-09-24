package com.example.java.assessment.repository;


import com.example.java.assessment.entity.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
import com.example.java.assessment.repository.impl.ProductCustomRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, UUID>, ProductCustomRepository {
}
