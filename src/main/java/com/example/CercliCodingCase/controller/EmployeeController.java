package com.example.CercliCodingCase.controller;

import com.example.CercliCodingCase.model.Employee;
import com.example.CercliCodingCase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing employees.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Add a new employee.
     */
    @PostMapping("/add")
    public Employee addEmployee(@RequestParam final String name,
                                @RequestParam final String position,
                                @RequestParam final String email,
                                @RequestParam final double salary,
                                @RequestParam final String countryId) {
        return employeeService.addEmployee(name, position, email, salary, countryId);
    }

    /**
     * Update an existing employee.
     */
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable final UUID id,
                                   @RequestParam final String name,
                                   @RequestParam final String position,
                                   @RequestParam final String email,
                                   @RequestParam final double salary,
                                   @RequestParam final String countryId) {
        return employeeService.updateEmployee(id, name, position, email, salary, countryId);
    }

    /**
     * Retrieve an employee by ID, converting timestamps to the user's local timezone.
     */
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable final UUID id,
                                @RequestParam final String timezone) {
        return employeeService.getEmployee(id, timezone);
    }

    /**
     * Get all employees.
     */
    @GetMapping("/all")
    public List<Employee> getAllEmployees(@RequestParam final String timezone) {
        return employeeService.getAllEmployees(timezone);
    }
}
