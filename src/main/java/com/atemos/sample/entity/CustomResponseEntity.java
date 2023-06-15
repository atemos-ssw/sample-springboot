package com.atemos.sample.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponseEntity {
    private String message;
    private String code;
    

//    public CustomResponseEntity(String code, String message) {
//    	this.code  = code;
//    	this.message = message;
//    }
     
}