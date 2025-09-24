package com.example.java.assessment.entity;



import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import java.util.UUID;


@Getter
@Setter
@Table("products")
public class Product {

    @Id
    private UUID id;
    private String bookTitle;
    private Double bookPrice;
    private Integer bookQuantity;
}
