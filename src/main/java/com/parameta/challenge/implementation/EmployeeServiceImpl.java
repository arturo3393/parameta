package com.parameta.challenge.implementation;

import com.parameta.challenge.domain.Employee;
import com.parameta.challenge.repository.EmployeeRepository;
import com.parameta.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the EmployeeService interface.
 * This class provides methods to interact with the Employee entity.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    /**
     * Repository for performing CRUD operations on Employee entities.
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Saves the given employee entity.
     *
     * @param employee The employee entity to save.
     */
    @Override
    public void save(final Employee employee) {
        employeeRepository.save(employee);
    }
}
