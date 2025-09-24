package com.example.java.assessment.controller;


import com.example.java.assessment.config.BaseResponse;
import com.example.java.assessment.entity.Customer;
import com.example.java.assessment.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Customer>> addCustomer(@RequestBody Customer customer) {
        Customer customerSaved = customerService.create(customer);
        BaseResponse<Customer> response = new BaseResponse<>(
                HttpStatus.CREATED.value(),
                "Customer created successfully",
                customerSaved
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<Customer>>> getAllCustomers() {
        List<Customer> customers = customerService.getAll();
        BaseResponse<List<Customer>> response = new BaseResponse<>(
                HttpStatus.OK.value(),
                "Customer list retrieved successfully",
                customers
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Customer>> getCustomerById(@PathVariable UUID id) {
        Customer customer = customerService.findById(id);

        BaseResponse<Customer> response = new BaseResponse<>(
                HttpStatus.CREATED.value(),
                "Customer retrieved successfully",
                customer
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Customer>> updateCustomer (@PathVariable UUID id, @RequestBody Customer customer) {

        Customer updated = customerService.update(id, customer);
        BaseResponse<Customer> response = new BaseResponse<>(
                HttpStatus.CREATED.value(),
                "Customer updated successfully",
                updated
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Customer>> deleteCustomer (@PathVariable UUID id) {
        customerService.delete(id);
        BaseResponse<Customer> response = new BaseResponse<>(
                HttpStatus.OK.value(),
                "Customer delete successfully",
                null
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
