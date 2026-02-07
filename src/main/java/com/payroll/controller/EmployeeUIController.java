package com.payroll.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.payroll.service.EmployeeService;
import com.payroll.entity.Employee;
import java.util.List;
@Controller
@RequestMapping("/employees")
public class EmployeeUIController {

    private final EmployeeService service;

    public EmployeeUIController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String showEmployees(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "employees";
    }

    @GetMapping("/new")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping
    public String saveEmployee(@ModelAttribute Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/update/{id}")
    public String showEditEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @PostMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee) {
        service.updateEmployee(id, employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "delete-employee";
    }
}
