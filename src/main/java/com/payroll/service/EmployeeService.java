package com.payroll.service;

import com.payroll.EmployeeNotFoundException;
import com.payroll.entity.Employee;
import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

}