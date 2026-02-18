package com.payroll.service;

import com.payroll.entity.Attendance;
import com.payroll.entity.Employee;
import com.payroll.repository.AttendanceRepository;
import com.payroll.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveAttendance(Long empId, String monthYear, int presentDays, int otHours) {

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Optional<Attendance> existingAttendance =
                attendanceRepository.findByEmployeeAndMonthYear(employee, monthYear);

        if (existingAttendance.isPresent()) {
            throw new RuntimeException("Attendance already exists for this month");
        }

        Attendance attendance = new Attendance(employee, monthYear, presentDays, otHours);

        attendanceRepository.save(attendance);
    }


    @Override
    public List<Attendance> getAttendanceByEmployee(Long empId) {

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return attendanceRepository.findByEmployee(employee);
    }
}
