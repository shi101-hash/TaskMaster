package com.example.employeeManagement;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(String id, Employee updatedEmployee) {
        return repository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setPosition(updatedEmployee.getPosition());
            employee.setSalary(updatedEmployee.getSalary());
            return repository.save(employee);
        }).orElse(null);
    }

    public void deleteEmployee(String id) {
        repository.deleteById(id);
    }

    public List<Employee> searchEmployees(String query) {
        return repository.findByNameContainingIgnoreCase(query);
    }

    public List<Employee> filterByPosition(String position) {
        return repository.findByPositionContainingIgnoreCase(position);
    }

    public long getTotalEmployees() {
        return repository.count();
    }

    public double getAverageSalary() {
        return repository.findAll().stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }
}
