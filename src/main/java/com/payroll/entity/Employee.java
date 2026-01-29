package com.payroll.entity;
import jakarta.persistence.*;
@Entity
@Table(name="employees")
public class Employee {
@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String name;
private String role;
private double salary;
public Employee(){}
    public Employee(String name,String role,double salary){
    this.name =name;
    this.role =role;
    this.salary=salary;
    }
}
