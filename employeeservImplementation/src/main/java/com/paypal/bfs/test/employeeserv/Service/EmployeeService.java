package com.paypal.bfs.test.employeeserv.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.Entiry.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.Exception.InvalidAddressException;
import com.paypal.bfs.test.employeeserv.Repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.ws.rs.NotFoundException;


@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Integer id){
        EmployeeEntity employeeEntity = employeeRepository.getEmployeeById(id);

        if(employeeEntity == null){
            throw new NotFoundException(String.format("Employee with id %d not found! ", id));
        }

        Employee response = new Employee();
        response.setFirstName(employeeEntity.getFirstName());
        response.setLastName(employeeEntity.getLastName());
        response.setDateOfBirth(employeeEntity.getDateOfBirth());
        Address address = null;

        try{
            address = new ObjectMapper().readValue(employeeEntity.getAddress(),Address.class);
        }
        catch (Exception ex){
            throw new InvalidAddressException("Fail to parse Address!");
        }

        response.setAddress(address);
        return response;
    }

    public Employee createEmployee(Employee employee){
        EmployeeEntity request = new EmployeeEntity();
        request.setFirstName(employee.getFirstName());
        request.setLastName(employee.getLastName());
        request.setDateOfBirth(employee.getDateOfBirth());

        try{
            String address = new ObjectMapper().writeValueAsString(employee.getAddress());
            request.setAddress(address);
        }
        catch (Exception ex){
            throw new InvalidAddressException("Fail to parse Address!");
        }

        EmployeeEntity entity = employeeRepository.save(request);
        Employee response = new Employee();
        response.setLastName(entity.getLastName());
        response.setFirstName(entity.getFirstName());
        response.setDateOfBirth(entity.getDateOfBirth());
        return response;
    }
}
