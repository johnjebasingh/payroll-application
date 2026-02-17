package com.payroll.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "emp_id", nullable = false, referencedColumnName = "id")
    private Employee employee;

    @Column(name = "month_year")
    private String monthYear;

    private int presentDays;
    private int otHours;

    // getters & setters
}