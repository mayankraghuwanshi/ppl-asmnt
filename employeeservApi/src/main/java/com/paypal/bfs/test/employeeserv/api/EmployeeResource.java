package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;



import javax.validation.Valid;



public interface EmployeeResource {


    @RequestMapping(value = "/v1/bfs/employees/{id}")
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") Integer id);


    @PostMapping(value = "/v1/bfs/employees/")
    ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee , BindingResult result);


}
