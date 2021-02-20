package com.paypal.bfs.test.employeeserv.Controller;

import com.paypal.bfs.test.employeeserv.Exception.ValidationException;
import com.paypal.bfs.test.employeeserv.Service.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResponseEntity<Employee> employeeGetById(Integer id){
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


    @Override
    @PostMapping(value = "/v1/bfs/employees/")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee, BindingResult result){
        System.out.println(employee);
        handleRequestError(result);
        Employee response = employeeService.createEmployee(employee);
        return new ResponseEntity<>(employee,HttpStatus.ACCEPTED);

    }

    private void handleRequestError(BindingResult result){
        if(result.hasErrors() && result.getFieldErrors()!=null ){
            Map<String,String> errors = new HashMap<>();
            for(FieldError error : result.getFieldErrors()){
                errors.put(
                        error.getField(),
                        error.getDefaultMessage());
            }
            throw new ValidationException(errors,"Request validation failed");
        }
    }
}
