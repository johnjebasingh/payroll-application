package com.payroll.service;

import com.payroll.dto.DashboardSummaryDTO;
import com.payroll.entity.Employee;

import java.util.List;
import java.util.Map;

public interface DashboardService {

    long getTotalEmployees();

    long getThisMonthPayrollCount();

    double getTotalSalaryPaid();

    List<Employee> getAllEmployees();
    Map<String, Double> getMonthlyPayrollExpenses();

}


