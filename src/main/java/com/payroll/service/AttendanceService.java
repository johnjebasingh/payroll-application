package com.payroll.service;

import com.payroll.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    void saveAttendance(Long empId, String monthYear, int presentDays, int otHours);

    List<Attendance> getAttendanceByEmployee(Long empId);
}

