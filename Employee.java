package com.example.employeeManagement;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    private String name;
    private String position;
    private Double salary;

    // Constructors
    public Employee() {
    }

    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
}