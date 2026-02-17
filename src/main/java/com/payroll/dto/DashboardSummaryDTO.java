package com.payroll.dto;

public class DashboardSummaryDTO {

    private long totalEmployees;
    private long totalPayrolls;

    public DashboardSummaryDTO(long totalEmployees,
                               long totalPayrolls) {

        this.totalEmployees = totalEmployees;
        this.totalPayrolls = totalPayrolls;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public long getTotalPayrolls() {
        return totalPayrolls;
    }
}
