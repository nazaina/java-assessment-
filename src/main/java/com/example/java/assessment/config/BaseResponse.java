package com.example.java.assessment.config;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BaseResponse<T> {

    private int code;
    private String message;
    private T data;


//    public BaseResponse(int code, String message, T data) {
//        this.code = code;
//        this.message = message;
//        this.data = data;
//
//    }
}