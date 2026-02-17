package com.payroll.controller;

import com.payroll.entity.Payroll;
import com.payroll.repository.EmployeeRepository;
import com.payroll.service.PayrollService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;


@Controller
@RequestMapping("/payroll")
public class PayrollController {



    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // Show bulk payroll page
    @GetMapping("/bulk")
    public String showBulkPayrollPage() {
        return "bulk-payroll";
    }

    // Run bulk payroll
    @PostMapping("/bulk/run")
    public String runBulkPayroll(
            @RequestParam String monthYear,
            Model model) {

        List<Payroll> payrolls = payrollService.runBulkPayroll(monthYear);

        model.addAttribute("monthYear", monthYear);
        model.addAttribute("payrolls", payrolls);
        model.addAttribute("count", payrolls.size());

        return "bulk-payroll-result";
    }


    // Generate payroll for single employee (HTML flow)
    @PostMapping("/generate/{empId}")
    public String generatePayrollForEmployee(
            @PathVariable Long empId,
            @RequestParam String monthYear,
            Model model) {


        Payroll payroll = payrollService.calculateAndSavePayroll(empId, monthYear);

        model.addAttribute("payroll", payroll);
        return "payroll-result";
    }
}