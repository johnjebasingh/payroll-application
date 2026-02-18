package com.payroll.entity;
import jakarta.persistence.*;
import java.time.YearMonth;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @Column(name = "month_year", nullable = false)
    private String monthYear;

    @Column(name = "present_days", nullable = false)
    private int presentDays;

    @Column(name = "ot_hours")
    private int otHours;

    // ================= CONSTRUCTORS =================

    public Attendance() {
    }

    public Attendance(Employee employee, String monthYear, int presentDays, int otHours) {
        this.employee = employee;
        this.monthYear = monthYear;
        this.presentDays = presentDays;
        this.otHours = otHours;
    }

    // ================= GETTERS AND SETTERS =================

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
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

    public int getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(int presentDays) {
        this.presentDays = presentDays;
    }

    public int getOtHours() {
        return otHours;
    }

    public void setOtHours(int otHours) {
        this.otHours = otHours;
    }
}
