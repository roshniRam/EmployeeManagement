package com.example.CercliCodingCase.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents an employee model
 */
@Getter
public class Employee {

    private final UUID employeeId;
    private final String name;
    private final String position;
    private final String email;
    private final double salary;
    private final String countryId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    public Employee(final UUID employeeId, final String name, final String position, final String email,
                    final double salary, final String countryId, final LocalDateTime createdAt, final LocalDateTime modifiedAt) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.email = email;
        this.salary = salary;
        this.countryId = countryId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    /**
     * Returns string representation of the employee.
     */
    @Override
    public String toString() {
        return String.format("Employee: employeeId=%s, name='%s', position='%s', email='%s', " +
                        "salary=%.2f, country='%s', createdAt='%s', modifiedAt='%s'",
                employeeId, name, position, email, salary, countryId, createdAt, modifiedAt);
    }
}
