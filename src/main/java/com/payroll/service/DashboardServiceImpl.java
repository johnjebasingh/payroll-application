package com.payroll.service;

import com.payroll.dto.DashboardSummaryDTO;
import com.payroll.entity.Employee;
import com.payroll.entity.Payroll;
import com.payroll.repository.EmployeeRepository;
import com.payroll.repository.PayrollRepository;
import com.payroll.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final EmployeeRepository employeeRepository;
    private final PayrollRepository payrollRepository;

    public DashboardServiceImpl(EmployeeRepository employeeRepository,
                                PayrollRepository payrollRepository) {
        this.employeeRepository = employeeRepository;
        this.payrollRepository = payrollRepository;
    }

    @Override
    public long getTotalEmployees() {
        return employeeRepository.count();
    }

    @Override
    public long getThisMonthPayrollCount() {
        return payrollRepository.count();
    }

    @Override
    public double getTotalSalaryPaid() {
        return payrollRepository.findAll()
                .stream()
                .mapToDouble(Payroll::getNetSalary)
                .sum();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public Map<String, Double> getMonthlyPayrollExpenses() {

        List<Object[]> results =
                payrollRepository.getMonthlyPayrollExpenses();

        Map<String, Double> map = new LinkedHashMap<>();

        for(Object[] row : results){
            String month = (String) row[0];
            Double total = (Double) row[1];
            map.put(month, total);
        }

        return map;
    }

}
