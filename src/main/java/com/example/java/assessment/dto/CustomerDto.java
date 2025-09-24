package com.example.java.assessment.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class CustomerDto {

        private UUID id;

//        @Schema(example = "John")
        private String firstName;

//        @Schema(example = "Doe")
        private String lastName;

//        @Schema(example = "john@example.com")
        private String email;

//        @Schema(example = "Kuala Lumpur")
        private String address;

//        @Schema(example = "01232432543")
        private String phoneNo;

}
