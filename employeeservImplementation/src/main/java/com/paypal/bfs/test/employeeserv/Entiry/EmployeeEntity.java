package com.paypal.bfs.test.employeeserv.Entiry;


import com.paypal.bfs.test.employeeserv.api.model.Address;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
public class EmployeeEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    @Lob
    private String address;



}
