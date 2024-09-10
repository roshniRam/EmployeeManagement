package com.example.CercliCodingCase;

import com.example.CercliCodingCase.model.Employee;
import com.example.CercliCodingCase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Driver code to test the employee service.
 */
public class EmployeeDriver {

    @Autowired
    private static EmployeeService employeeService = new EmployeeService();

    public static void main(String[] args) throws Exception {
        // Add a new employee
        Employee newEmployee = employeeService.addEmployee("John Doe", "Software Engineer", "john.doe@example.com", 100000, "IN");
        System.out.println("Added Employee: " + newEmployee);

        // Update the employee's information
        Employee updatedEmployee = employeeService.updateEmployee(newEmployee.getEmployeeId(), "John Doe", "Senior Engineer", "john.doe@example.com", 120000, "US");
        System.out.println("Updated Employee: " + updatedEmployee);

        // Retrieve the employee with a different timezone
        Employee retrievedEmployee = employeeService.getEmployee(updatedEmployee.getEmployeeId(), "America/New_York");
        System.out.println("Retrieved Employee: " + retrievedEmployee);

        // Retrieve the employee with a different timezone
        Employee retrievedEmployee2 = employeeService.getEmployee(updatedEmployee.getEmployeeId(), "Asia/Dubai");
        System.out.println("Retrieved Employee: " + retrievedEmployee2);

        // Add a new employee
        Employee employee2 = employeeService.addEmployee("Emily", "Software Engineer 2", "emily@example.com", 100000, "US");
        Employee employee3 = employeeService.addEmployee("Dave", "Staff Software Engineer", "dave@example.com", 500000, "IN");

        System.out.println("All Employees: \n" + employeeService.getAllEmployeesAsString("Asia/Dubai"));
    }
}

