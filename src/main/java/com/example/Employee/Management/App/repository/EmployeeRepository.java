package com.example.Employee.Management.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Employee.Management.App.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}