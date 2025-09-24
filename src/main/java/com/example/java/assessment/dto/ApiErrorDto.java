package com.example.java.assessment.dto;

import lombok.Data;

@Data
public class ApiErrorDto {

    public Integer code;
    public String message;

    public ApiErrorDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
