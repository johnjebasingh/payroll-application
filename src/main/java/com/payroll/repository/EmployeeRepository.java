package com.payroll.repository;
import com.payroll.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends
    JpaRepository<Employee,Long>{
    
}

