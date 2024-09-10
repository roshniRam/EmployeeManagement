package com.example.CercliCodingCase.service;

import com.example.CercliCodingCase.model.Employee;
import com.example.CercliCodingCase.utility.EmailUtil;
import com.example.CercliCodingCase.utility.TimeZoneUtil;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service class for managing employees
 */
@Service
@NoArgsConstructor
public class EmployeeService {

    private final Map<UUID, Employee> employeeStore = new HashMap<>();

    /**
     * add new employee
     */
    public Employee addEmployee(final String name, final String position, final String email,
                                final double salary, final String countryId) {
        if (!EmailUtil.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }

        final LocalDateTime utcNow = TimeZoneUtil.getServerDateTime();

        // Create and store employee
        final Employee employee = Employee.builder()
                .employeeId(UUID.randomUUID())
                .name(name)
                .position(position)
                .email(email)
                .salary(salary)
                .countryId(countryId)
                .createdAt(utcNow)
                .modifiedAt(utcNow)
                .build();

        employeeStore.put(employee.getEmployeeId(), employee);
        return employee;
    }

    /**
     * update existing employee
     */
    public Employee updateEmployee(final UUID employeeId, final String name, final String position,
                                   final String email, final double salary, final String countryId) {

        final Employee existingEmployee = employeeStore.get(employeeId);
        if (existingEmployee == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        if (!EmailUtil.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }

        // Update the employee details and store the updated employee
        final LocalDateTime now = TimeZoneUtil.getServerDateTime();
        final Employee updatedEmployee = Employee.builder()
                .employeeId(employeeId)
                .name(name)
                .position(position)
                .email(email)
                .salary(salary)
                .countryId(countryId)
                .createdAt(existingEmployee.getCreatedAt())
                .modifiedAt(now)
                .build();

        employeeStore.put(employeeId, updatedEmployee);

        return updatedEmployee;
    }


    /**
     * Get an employee by ID, converting timestamps to the user's local timezone.
     */
    public Employee getEmployee(final UUID employeeId, final String timezone) {
        final Employee employee = employeeStore.get(employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        // Convert createdAt and modifiedAt to the user's local timezone
        final ZoneId localZoneId = ZoneId.of(timezone);
        final LocalDateTime localCreatedAt = TimeZoneUtil.convertUtcToLocal(employee.getCreatedAt(), localZoneId);
        final LocalDateTime localModifiedAt = TimeZoneUtil.convertUtcToLocal(employee.getModifiedAt(), localZoneId);

        return Employee.builder()
                .employeeId(employee.getEmployeeId())
                .name(employee.getName())
                .position(employee.getPosition())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .countryId(employee.getCountryId())
                .createdAt(localCreatedAt)
                .modifiedAt(localModifiedAt)
                .build();
    }

    /**
     * Get all employees.
     */
    public List<Employee> getAllEmployees(final String timezone) {
        final ZoneId localZoneId = ZoneId.of(timezone);

        return employeeStore.values().stream()
                .map(employee -> {
                    // Convert timestamps from UTC to local time zone
                    LocalDateTime localCreatedAt = TimeZoneUtil.convertUtcToLocal(employee.getCreatedAt(), localZoneId);
                    LocalDateTime localModifiedAt = TimeZoneUtil.convertUtcToLocal(employee.getModifiedAt(), localZoneId);

                    return Employee.builder()
                            .employeeId(employee.getEmployeeId())
                            .name(employee.getName())
                            .position(employee.getPosition())
                            .email(employee.getEmail())
                            .salary(employee.getSalary())
                            .countryId(employee.getCountryId())
                            .createdAt(localCreatedAt)
                            .modifiedAt(localModifiedAt)
                            .build();
                })
                .collect(Collectors.toList());
    }

    /**
     * Get string representation of all employees.
     */
    public String getAllEmployeesAsString(final String timezone) {
        final List<Employee> allEmployees = getAllEmployees(timezone);

        StringBuilder employeeStringBuilder = new StringBuilder();
        for (Employee employee : allEmployees) {
            employeeStringBuilder.append(employee.toString()).append("\n");
        }

        return employeeStringBuilder.toString();
    }

}
