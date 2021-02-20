package com.paypal.bfs.test.employeeserv.test;


import com.paypal.bfs.test.employeeserv.Entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.Repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.Service.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class employeeservTest {

    @Autowired
    EmployeeService service ;

    @MockBean
    EmployeeRepository repository;

    static final String ADDRESS_STRING = "{\"line1\":\"Himgiri\",\"line2\":\"Panki\",\"city\":\"Kanpur\",\"country\":\"India\",\"zip_code\":\"208020\"}";

    @Test
    public void addressObjToStr(){
        Address address = new Address();
        address.setLine1("Himgiri");
        address.setLine2("Panki");
        address.setCity("Kanpur");
        address.setCountry("India");
        address.setZipCode("208020");
        assertEquals(ADDRESS_STRING,service.mapAddressObjToStr(address));
    }

    @Test
    public void addressStrToObj(){
        Address address = new Address();
        address.setLine1("Himgiri");
        address.setLine2("Panki");
        address.setCity("Kanpur");
        address.setCountry("India");
        address.setZipCode("208020");
        assertEquals(address,service.mapAddressStrToObj(ADDRESS_STRING));
    }



    @Test
    public void FindEmpByIdTest(){
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(2);
        entity.setFirstName("Mayank");
        entity.setLastName("Raghuvanshi");
        entity.setAddress("{\"line1\":\"Himgiri\",\"line2\":\"Panki\",\"city\":\"Kanpur\",\"country\":\"india\",\"zip_code\":\"208020\"}");
        when(repository.getEmployeeById(1)).thenReturn(entity);
        assertEquals(repository.getEmployeeById(1),entity);
    }

    @Test
    public void createEmpTest(){

        EmployeeEntity entity = new EmployeeEntity();
        entity.setAddress(ADDRESS_STRING);
        entity.setFirstName("Mayank");
        entity.setLastName("Raghuvanshi");
        entity.setDateOfBirth(new Date());
        when(repository.save(entity)).thenReturn(entity);
        Employee employee = new Employee();
        employee.setFirstName(entity.getFirstName());
        employee.setLastName(entity.getLastName());
        employee.setDateOfBirth(entity.getDateOfBirth());
        employee.setAddress(service.mapAddressStrToObj(entity.getAddress()));
        assertEquals(employee,service.createEmployee(employee));

    }

}
