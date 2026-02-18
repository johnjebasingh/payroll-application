package com.payroll.controller;

import com.payroll.entity.Attendance;
import com.payroll.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceRestController {

    private final AttendanceService attendanceService;

    public AttendanceRestController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // SAVE ATTENDANCE
    @PostMapping("/save")
    public ResponseEntity<String> saveAttendance(@RequestParam Long empId,
                                                 @RequestParam String monthYear,
                                                 @RequestParam int presentDays,
                                                 @RequestParam int otHours) {

        attendanceService.saveAttendance(empId, monthYear, presentDays, otHours);

        return ResponseEntity.ok("Attendance Saved Successfully");
    }

    // GET ATTENDANCE BY EMPLOYEE
    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<Attendance>> getAttendance(@PathVariable Long empId) {

        List<Attendance> list = attendanceService.getAttendanceByEmployee(empId);

        return ResponseEntity.ok(list);
    }
}

