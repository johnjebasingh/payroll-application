package com.payroll.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Homecontroller {
    @GetMapping("/")
    public String home(){
        return "payroll System is running";
    }
}
