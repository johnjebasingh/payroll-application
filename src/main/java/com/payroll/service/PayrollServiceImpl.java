package com.payroll.service;

import com.payroll.entity.Attendance;
import com.payroll.entity.Employee;
import com.payroll.entity.Payroll;
import com.payroll.repository.AttendanceRepository;
import com.payroll.repository.EmployeeRepository;
import com.payroll.repository.PayrollRepository;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final PayrollRepository payrollRepository;

    public PayrollServiceImpl(EmployeeRepository employeeRepository,
                              AttendanceRepository attendanceRepository,
                              PayrollRepository payrollRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
        this.payrollRepository = payrollRepository;
    }

    // =========================
    // SINGLE EMPLOYEE PAYROLL
    // =========================
    @Override
    public Payroll calculateAndSavePayroll(Long empId, String monthYear) {

        // 1️⃣ Prevent duplicate payroll
        if (payrollRepository.existsByEmployee_IdAndMonthYear(empId, monthYear)) {
            throw new RuntimeException("Payroll already exists for this month");
        }

        // 2️⃣ Get employee
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // 3️⃣ Get attendance
        Attendance attendance = attendanceRepository
                .findByEmployee_IdAndMonthYear(empId, monthYear)
                .orElseThrow(() -> {
                    System.out.println("DEBUG: Attendance missing for empId=" + empId + " month=" + monthYear);
                    return new RuntimeException("Attendance not found");
                });


        // 4️⃣ Salary calculation
        double perDaySalary = employee.getSalary() / 30;
        double earnedSalary = perDaySalary * attendance.getPresentDays();
        double otAmount = attendance.getOtHours() * 150;
        double netSalary = earnedSalary + otAmount;

        // 5️⃣ Create payroll
        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setMonthYear(monthYear);
        payroll.setEarnedSalary(earnedSalary);
        payroll.setOtAmount(otAmount);
        payroll.setAdditionalEarnings(0);
        payroll.setDeductions(0);
        payroll.setNetSalary(netSalary);
        payroll.setPayrollLogged(true);

        // 6️⃣ Save
        return payrollRepository.save(payroll);
    }

    // =========================
    // BULK PAYROLL
    // =========================
    @Override
    public List<Payroll> runBulkPayroll(String monthYear) {

        List<Employee> employees = employeeRepository.findAll();
        List<Payroll> processedPayrolls = new ArrayList<>();

        for (Employee employee : employees) {

            Long empId = employee.getId();

            if (payrollRepository.existsByEmployee_IdAndMonthYear(empId, monthYear)) {
                continue;
            }

            Optional<Attendance> attendanceOpt =
                    attendanceRepository. findByEmployee_IdAndMonthYear(empId, monthYear);

            if (attendanceOpt.isEmpty()) {
                continue;
            }

            Payroll payroll = calculateAndSavePayroll(empId, monthYear);
            processedPayrolls.add(payroll);
        }

        // 🔥 IMPORTANT PART
        if (processedPayrolls.isEmpty()) {
            return payrollRepository.findByMonthYear(monthYear);
        }

        return processedPayrolls;
    }
    }
