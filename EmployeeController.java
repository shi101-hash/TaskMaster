package com.example.employeeManagement;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        model.addAttribute("totalEmployees", service.getTotalEmployees());
        model.addAttribute("averageSalary", service.getAverageSalary());
        return "employees";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        service.addEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Employee employee = service.getAllEmployees().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("employee", employee);
        return "add-employee";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable String id, @ModelAttribute Employee employee) {
        service.updateEmployee(id, employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        service.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/search")
    public String searchEmployees(@RequestParam String query, Model model) {
        List<Employee> results = service.searchEmployees(query);
        model.addAttribute("employees", results);
        model.addAttribute("totalEmployees", service.getTotalEmployees());
        model.addAttribute("averageSalary", service.getAverageSalary());
        return "employees";
    }
}
