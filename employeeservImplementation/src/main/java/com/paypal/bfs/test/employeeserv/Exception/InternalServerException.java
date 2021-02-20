package com.paypal.bfs.test.employeeserv.Exception;


import lombok.Data;

@Data
public class InternalServerException extends RuntimeException{
    private String code;
    public InternalServerException(String code){
        super("Internal server exception");
        this.code = code;
    }
}
