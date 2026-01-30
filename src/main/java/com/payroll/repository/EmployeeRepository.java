package com.payroll.repository;
import com.payroll.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends
    JpaRepository<Employee,Long>{
    
}

