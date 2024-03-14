package com.parameta.challenge.implementation;

import com.parameta.challenge.domain.Employee;
import com.parameta.challenge.repository.EmployeeRepository;
import com.parameta.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> findEmployeeById(final String employeeId) {
        var employee = employeeRepository.findById(employeeId);


        return employee;
    }
}
