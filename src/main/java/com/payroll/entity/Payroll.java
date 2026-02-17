package com.payroll.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;

@Getter
@Setter

@AllArgsConstructor
@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id")
    private Long payrollid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @Column(name = "month_year", nullable = false)
    private String monthYear;   // format: YYYY-MM

    @Column(nullable = false)
    private double earnedSalary;

    @Column(nullable = false)
    private double otAmount;

    @Column(nullable = false)
    private double additionalEarnings;

    @Column(nullable = false)
    private double deductions;

    @Column(nullable = false)
    private double netSalary;

    @Column(nullable = false)
    private boolean payrollLogged;

    // ---- constructors ----
    public Payroll() {}

    // ---- getters & setters ----
    public Long getId() {
        return payrollid;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public double getEarnedSalary() {
        return earnedSalary;
    }

    public void setEarnedSalary(double earnedSalary) {
        this.earnedSalary = earnedSalary;
    }

    public double getOtAmount() {
        return otAmount;
    }

    public void setOtAmount(double otAmount) {
        this.otAmount = otAmount;
    }

    public double getAdditionalEarnings() {
        return additionalEarnings;
    }

    public void setAdditionalEarnings(double additionalEarnings) {
        this.additionalEarnings = additionalEarnings;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public boolean isPayrollLogged() {
        return payrollLogged;
    }

    public void setPayrollLogged(boolean payrollLogged) {
        this.payrollLogged = payrollLogged;
    }
}