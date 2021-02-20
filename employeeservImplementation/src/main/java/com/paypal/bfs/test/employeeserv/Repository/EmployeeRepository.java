package com.paypal.bfs.test.employeeserv.Repository;

import com.paypal.bfs.test.employeeserv.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {

    EmployeeEntity getEmployeeById(Integer id);
}
