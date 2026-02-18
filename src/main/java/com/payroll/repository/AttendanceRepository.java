package com.payroll.repository;

import com.payroll.entity.Attendance;
import com.payroll.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByEmployee(Employee employee);

    Optional<Attendance> findByEmployeeAndMonthYear(Employee employee, String monthYear);
}