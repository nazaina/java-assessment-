package com.example.java.assessment.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {

    private UUID id;
    private String bookTitle;
    private Double bookPrice;
    private Integer bookQuantity;
}
