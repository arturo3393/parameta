package com.parameta.challenge.repository;

import com.parameta.challenge.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Employee entities.
 * This interface extends JpaRepository to leverage Spring Data JPA functionality.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
