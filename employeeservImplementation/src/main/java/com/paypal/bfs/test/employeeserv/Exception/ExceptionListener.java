package com.paypal.bfs.test.employeeserv.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.ws.rs.NotFoundException;


@ControllerAdvice
@RestController
public class ExceptionListener extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ErrorDetails> requestValidationException(ValidationException e , WebRequest request) {
        ErrorDetails response = new ErrorDetails();
        response.setErrors(e.getErrors());
        response.setMessage(e.getMessage());
        return new ResponseEntity<ErrorDetails>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorDetails> resourceNotFoundException(NotFoundException e , WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InternalServerException.class})
    public ResponseEntity<ErrorDetails> handleInternalException(InternalServerException e , WebRequest request){
        ErrorDetails response = new ErrorDetails();
        response.setMessage(e.getMessage());
        response.setCode(e.getCode());
        return new ResponseEntity<ErrorDetails>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Internal server exception!");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
