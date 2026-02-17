package com.payroll.controller;

import com.payroll.dto.DashboardSummaryDTO;
import com.payroll.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public String showDashboard(Model model) {

        model.addAttribute("totalEmployees",
                dashboardService.getTotalEmployees());

        model.addAttribute("thisMonthPayroll",
                dashboardService.getThisMonthPayrollCount());

        model.addAttribute("totalSalary",
                dashboardService.getTotalSalaryPaid());

        model.addAttribute("employees",
                dashboardService.getAllEmployees());
        model.addAttribute("months",
                List.of("Jan","Feb","Mar","Apr","May","Jun"));

        model.addAttribute("monthlyExpenses",
                List.of(120000,150000,180000,160000,200000,220000));
        Map<String, Double> chartData =
                dashboardService.getMonthlyPayrollExpenses();

        model.addAttribute("months",
                new ArrayList<>(chartData.keySet()));

        model.addAttribute("monthlyExpenses",
                new ArrayList<>(chartData.values()));



        return "dashboard";
    }
}

