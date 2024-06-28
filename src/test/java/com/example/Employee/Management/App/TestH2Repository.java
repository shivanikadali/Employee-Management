package com.example.Employee.Management.App;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Employee.Management.App.entity.Employee;

public interface TestH2Repository extends JpaRepository<Employee, Integer> {
    
}
