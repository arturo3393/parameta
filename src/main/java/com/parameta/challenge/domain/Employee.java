package com.parameta.challenge.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Table
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

