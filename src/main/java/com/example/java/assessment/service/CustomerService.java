package com.example.java.assessment.service;

import com.example.java.assessment.config.ResourceNotFoundException;
import com.example.java.assessment.entity.Customer;
import com.example.java.assessment.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer c){
        return customerRepository.save(c);
    }

    public Customer update(UUID id, Customer c){
        return customerRepository.findById(id).map(exist -> {
            exist.setFirstName(c.getFirstName());
            exist.setLastName(c.getLastName());
            exist.setEmail(c.getEmail());
            exist.setAddress(c.getAddress());
            exist.setPhoneNo(c.getPhoneNo());

            return customerRepository.save(exist);
        }).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer findById(UUID id){
        return customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
    }

    public void delete(UUID id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }
}
