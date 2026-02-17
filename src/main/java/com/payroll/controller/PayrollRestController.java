package com.payroll.controller;

import com.payroll.entity.Payroll;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.payroll.service.PayrollService;

import java.util.List;
@RestController
@RequestMapping("/api/payroll")
public class PayrollRestController {

    private final PayrollService payrollService;

    public PayrollRestController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // Bulk payroll API
    @PostMapping("/bulk/run")
    public ResponseEntity<List<Payroll>> runBulkPayroll(
            @RequestParam String monthYear) {

        List<Payroll> payrolls = payrollService.runBulkPayroll(monthYear);
        return ResponseEntity.ok(payrolls);
    }

    // Single employee payroll API
    @PostMapping("/generate/{empId}")
    public ResponseEntity<Payroll> generatePayroll(
            @PathVariable Long empId,
            @RequestParam String monthYear) {

        Payroll payroll = payrollService.calculateAndSavePayroll(empId, monthYear);
        return ResponseEntity.ok(payroll);
    }
}
