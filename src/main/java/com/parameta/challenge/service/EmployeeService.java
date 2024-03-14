package com.parameta.challenge.service;

import com.parameta.challenge.domain.Employee;


import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findEmployeeById(String employeeId);
}
