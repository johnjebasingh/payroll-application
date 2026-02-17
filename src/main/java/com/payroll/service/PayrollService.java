package com.payroll.service;


import java.util.List;
import java.util.Optional;

import com.payroll.entity.Attendance;
import com.payroll.entity.Payroll;

public interface PayrollService {

    // Single employee payroll
    Payroll calculateAndSavePayroll(Long empId, String monthYear);

    List<Payroll> runBulkPayroll(String monthYear);
}