package com.example.java.assessment.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String bookTitle;
    private Double bookPrice;
    private Integer bookQuantity;
}
