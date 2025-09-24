package com.example.java.assessment.service;

import com.example.java.assessment.config.ResourceNotFoundException;
import com.example.java.assessment.entity.Customer;
import com.example.java.assessment.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> create(Customer customer) {

        return customerRepository.insert(customer);
    }

    public Mono<Customer> update(UUID id, Customer c) {
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Customer not found")))
                .flatMap(exist -> {
                    exist.setFirstName(c.getFirstName());
                    exist.setLastName(c.getLastName());
                    exist.setEmail(c.getEmail());
                    exist.setAddress(c.getAddress());
                    exist.setPhoneNo(c.getPhoneNo());
                    return customerRepository.save(exist);
                });
    }

    public Flux<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Mono<Customer> findById(UUID id){
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Customer not found")));
    }

    public Mono<Void> delete(UUID id) {

        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Customer not found")))
                .flatMap(customerRepository::delete);
    }
}
