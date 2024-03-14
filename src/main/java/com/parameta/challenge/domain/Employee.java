package com.parameta.challenge.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public record Employee(
        @Id
        @Column(nullable = false)
        String id,
        String name,
        String lastName,
        LocalDate dateOfBirth,
        LocalDate hiringDate,
        String jobTitle,
        Double salary
) {
}

