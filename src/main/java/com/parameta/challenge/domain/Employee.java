package com.parameta.challenge.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


/**
 * Entity class representing an employee.
 * This class defines the structure of an employee entity, including attributes such as ID, name, date of birth, etc.
 */

@Data
@Table
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @Column(nullable = false)
    private String id;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate hiringDate;
    private String jobTitle;
    private Double salary;
}

