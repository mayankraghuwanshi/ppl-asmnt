package com.paypal.bfs.test.employeeserv.Exception;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException{
    public ValidationException(String result){
        super(result);
    }
}
