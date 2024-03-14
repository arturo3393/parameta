package com.parameta.challenge.service;

import com.parameta.challenge.domain.Employee;


import java.util.Optional;

/**
 * Service interface for managing employee-related operations.
 */
public interface EmployeeService {
    /**
     * Saves the given employee entity.
     *
     * @param employee The employee entity to save.
     */
    void save(Employee employee);
}
