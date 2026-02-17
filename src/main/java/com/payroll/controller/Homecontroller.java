package com.payroll.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
@Controller
public class Homecontroller {
    @GetMapping("/")
    public String home(){
        return "index";
    }
}
