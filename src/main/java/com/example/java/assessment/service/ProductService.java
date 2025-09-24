package com.example.java.assessment.service;

import com.example.java.assessment.config.ResourceNotFoundException;
import com.example.java.assessment.entity.Product;
import com.example.java.assessment.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product c){
        return productRepository.save(c);
    }

    public Product update(UUID id, Product c){
        return productRepository.findById(id).map(exist -> {
           exist.setBookTitle(c.getBookTitle());
           exist.setBookPrice(c.getBookPrice());
           exist.setBookQuantity(c.getBookQuantity());

            return productRepository.save(exist);
        }).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product findById(UUID id){
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
    }

    public void delete(UUID id){
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}
