package com.paypal.bfs.test.employeeserv.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.Entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.Exception.InternalServerException;
import com.paypal.bfs.test.employeeserv.Repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paypal.bfs.test.employeeserv.Constants.ExceptionConstant;
import javax.ws.rs.NotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Integer employeeId){

        logger.info("GetEmpById request with id " , employeeId);
        EmployeeEntity employeeEntity = employeeRepository.getEmployeeById(employeeId);

        if(employeeEntity == null){
            logger.info("Employee not found!");
            throw new NotFoundException(String.format(ExceptionConstant.EMPLOYEE_WITH_ID_NOT_FOUND,employeeId));

        }

        logger.info("Employee entity {} ",employeeEntity);

        Employee response = new Employee();
        response.setFirstName(employeeEntity.getFirstName());
        response.setLastName(employeeEntity.getLastName());
        response.setDateOfBirth(dateObjToStr(employeeEntity.getDateOfBirth()));
        response.setAddress(mapAddressStrToObj(employeeEntity.getAddress()));

        logger.info("GetEmpById response {}", response);
        return response;
    }

    public Employee createEmployee(Employee request){
        logger.info("createEmployee request {}",request);
        EmployeeEntity entityRequest = new EmployeeEntity();
        entityRequest.setFirstName(request.getFirstName());
        entityRequest.setLastName(request.getLastName());
        entityRequest.setDateOfBirth(dateStrToObj(request.getDateOfBirth()));
        entityRequest.setAddress(mapAddressObjToStr(request.getAddress()));
        EmployeeEntity entityResponse = employeeRepository.save(entityRequest);
        if(entityResponse == null){
            logger.error("Failed to create employee {}",entityRequest);
            throw new InternalServerException(ExceptionConstant.FAILED_TO_CREATE_NEW_EMPLOYEE);
        }
        Employee response = new Employee();
        response.setLastName(entityResponse.getLastName());
        response.setFirstName(entityResponse.getFirstName());
        response.setDateOfBirth(dateObjToStr(entityResponse.getDateOfBirth()));
        response.setAddress(mapAddressStrToObj(entityResponse.getAddress()));

        logger.info("createEmployee response {}" , response);
        return response;
    }


    public String mapAddressObjToStr(Address address){
        String addressString = "";
        try{
            addressString =  new ObjectMapper().writeValueAsString(address);
        }
        catch (Exception ex){
            logger.error("Failed to cast address object to string!");
            throw new InternalServerException(ExceptionConstant.FAILED_TO_CAST_ADDRESS_OBJECT_TO_STRING);
        }

        return addressString;
    }


    public Address mapAddressStrToObj(String address){
        Address obj = null;
        try{
            obj = new ObjectMapper().readValue(address,Address.class);
        }
        catch (Exception ex){
            logger.error("Failed to cast address string to object!");
            throw new InternalServerException(ExceptionConstant.FAILED_TO_CAST_ADDRESS_STRING_TO_OBJECT);
        }
        return obj;
    }

    public Date dateStrToObj(String str){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try{
            date = format.parse(str);
        }
        catch (Exception ex){
            logger.error("Failed to convert date str to obj");
            throw new InternalServerException(ExceptionConstant.FAILED_TO_CONVERT_DATE_STR_TO_OBJ);
        }
        return date;
    }

    public String dateObjToStr(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String str = "";
        try{
            str = format.format(date);
        }
        catch (Exception ex){
            logger.error("Failed to convert date obj to str");
            throw new InternalServerException(ExceptionConstant.FAILED_TO_CONVERT_DATE_OBJ_TO_STR);
        }
        return  str;
    }

}
