package com.paypal.bfs.test.employeeserv.Exception;

public class InvalidAddressException extends RuntimeException{

    public InvalidAddressException(String message){
        super(message);
    }
}
