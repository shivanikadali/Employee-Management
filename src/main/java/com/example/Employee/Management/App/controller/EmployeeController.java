package com.example.Employee.Management.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee.Management.App.entity.Employee;
import com.example.Employee.Management.App.repository.EmployeeRepository;
import com.example.Employee.Management.App.service.EmployeeService;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployeeDetails() {
        return employeeService.getAllEmployee();
    }

    @PostMapping
    public Employee insertEmployeeDetails(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

}
