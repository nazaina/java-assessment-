package com.example.java.assessment.controller;


import com.example.java.assessment.config.BaseResponse;
import com.example.java.assessment.entity.Customer;
import com.example.java.assessment.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<BaseResponse<Object>>> addCustomer(@RequestBody Customer customer) {

        return customerService.create(customer)
                .map(data -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(HttpStatus.CREATED.value());
                    baseResponse.setMessage("Customer created successfully");
                    baseResponse.setData(data);
                    return ResponseEntity.ok(baseResponse);
                })
                .onErrorResume(throwable -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(2000);
                    baseResponse.setData(throwable.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body(baseResponse));
                });



    }

    @GetMapping
    public Mono<ResponseEntity<BaseResponse<Object>>> getAllCustomers() {
        return customerService.getAll()
                .collectList()
                .map(customers -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(HttpStatus.OK.value());
                    baseResponse.setMessage("Customer list retrieved successfully");
                    baseResponse.setData(customers);

                    return ResponseEntity.ok(baseResponse);
                })
                .onErrorResume(throwable -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(2000);
                    baseResponse.setMessage(throwable.getMessage());
                    baseResponse.setData(null);

                    return Mono.just(ResponseEntity.internalServerError().body(baseResponse));
                });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BaseResponse<Object>>> getCustomerById(@PathVariable UUID id) {
        return customerService.findById(id) // returns Mono<Customer>
                .map(customer -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(HttpStatus.OK.value());
                    baseResponse.setMessage("Customer retrieved successfully");
                    baseResponse.setData(customer);
                    return ResponseEntity.ok(baseResponse);
                })
                .onErrorResume(throwable -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(2000);
                    baseResponse.setData(throwable.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body(baseResponse));
                });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<BaseResponse<Object>>> updateCustomer (@PathVariable UUID id, @RequestBody Customer customer) {

        return customerService.update(id,customer)
                .map(data -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(HttpStatus.OK.value());
                    baseResponse.setMessage("Customer updated successfully");
                    baseResponse.setData(data);
                    return ResponseEntity.ok(baseResponse);
                })
                .onErrorResume(throwable -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(2000);
                    baseResponse.setData(throwable.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body(baseResponse));
                });


    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<BaseResponse<Object>>> deleteCustomer (@PathVariable UUID id) {
        return customerService.delete(id) // returns Mono<Customer>
                .map(customer -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(HttpStatus.OK.value());
                    baseResponse.setMessage("Customer deleted successfully");
                    baseResponse.setData(null);
                    return ResponseEntity.ok(baseResponse);
                })
                .onErrorResume(throwable -> {
                    BaseResponse<Object> baseResponse = new BaseResponse<>();
                    baseResponse.setCode(2000);
                    baseResponse.setData(throwable.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body(baseResponse));
                });


    }
}
