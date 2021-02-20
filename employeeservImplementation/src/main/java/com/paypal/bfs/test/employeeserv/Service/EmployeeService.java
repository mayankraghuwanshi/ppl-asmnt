package com.paypal.bfs.test.employeeserv.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.Entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.Repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import javax.ws.rs.NotFoundException;


@Service
public class EmployeeService {

    private static final String EMP_NOT_FOUND_ERROR_MSG = "Employee with id %d not found!";
    private static final String FAIL_TO_MAP_ADDRESS_STR_TO_OBJ = "Failed to parse Address!";
    private static final String FAIL_TO_MAP_ADDRESS_OBJ_TO_STR = "Failed to parse Address";


    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Integer id){
        EmployeeEntity employeeEntity = employeeRepository.getEmployeeById(id);

        if(employeeEntity == null){
            throw new NotFoundException(String.format(EMP_NOT_FOUND_ERROR_MSG, id));
        }

        Employee response = new Employee();
        response.setFirstName(employeeEntity.getFirstName());
        response.setLastName(employeeEntity.getLastName());
        response.setDateOfBirth(employeeEntity.getDateOfBirth());
        response.setAddress(mapAddressStrToObj(employeeEntity.getAddress()));
        return response;
    }

    public Employee createEmployee(Employee employee){
        EmployeeEntity request = new EmployeeEntity();
        request.setFirstName(employee.getFirstName());
        request.setLastName(employee.getLastName());
        request.setDateOfBirth(employee.getDateOfBirth());
        request.setAddress(mapAddressObjToStr(employee.getAddress()));
        EmployeeEntity entity = employeeRepository.save(request);
        Employee response = new Employee();
        response.setLastName(entity.getLastName());
        response.setFirstName(entity.getFirstName());
        response.setDateOfBirth(entity.getDateOfBirth());
        response.setAddress(mapAddressStrToObj(entity.getAddress()));
        return response;
    }


    public String mapAddressObjToStr(Address address){
        String addressString = "";
        try{
            addressString =  new ObjectMapper().writeValueAsString(address);
        }
        catch (Exception ex){
            throw new ValidationException(FAIL_TO_MAP_ADDRESS_OBJ_TO_STR);
        }

        return addressString;
    }


    public Address mapAddressStrToObj(String address){
        Address obj = null;
        try{
            obj = new ObjectMapper().readValue(address,Address.class);
        }
        catch (Exception ex){
            throw new ValidationException(FAIL_TO_MAP_ADDRESS_STR_TO_OBJ);
        }
        return obj;
    }

}
