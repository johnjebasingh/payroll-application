package com.payroll.controller;
;
import com.payroll.repository.EmployeeRepository;
import com.payroll.service.AttendanceService;
import org.springframework.stereotype.Controller;
import com.payroll.entity.Attendance;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EmployeeRepository employeeRepository;

    public AttendanceController(AttendanceService attendanceService,
                                EmployeeRepository employeeRepository) {
        this.attendanceService = attendanceService;
        this.employeeRepository = employeeRepository;
    }

    // Show Attendance Form
    @GetMapping("/add")
    public String showAttendanceForm(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "add-attendance";
    }

    // Save Attendance
    @PostMapping("/save")
    public String saveAttendance(@RequestParam Long empId,
                                 @RequestParam String monthYear,
                                 @RequestParam int presentDays,
                                 @RequestParam int otHours) {

        attendanceService.saveAttendance(empId, monthYear, presentDays, otHours);

        return "redirect:/attendance/add";
    }

    // View Attendance by Employee
    @GetMapping("/employee/{empId}")
    public String viewAttendance(@PathVariable Long empId, Model model) {

        List<Attendance> attendanceList = attendanceService.getAttendanceByEmployee(empId);

        model.addAttribute("attendanceList", attendanceList);

        return "view-attendance";
    }
}

