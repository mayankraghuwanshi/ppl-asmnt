package com.paypal.bfs.test.employeeserv.Exception;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationException extends RuntimeException{
    private Map<String,String> errors;
    public ValidationException(Map<String,String> errors , String message){
        super(message);
        this.errors = errors;
    }
}
