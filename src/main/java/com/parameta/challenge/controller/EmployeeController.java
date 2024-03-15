package com.parameta.challenge.controller;

import static com.parameta.challenge.utils.Constants.ADULT_AGE;
import static com.parameta.challenge.utils.EmployeeUtils.*;

import com.parameta.challenge.domain.Employee;
import com.parameta.challenge.domain.ResponseDTO;
import com.parameta.challenge.service.EmployeeService;
import com.parameta.challenge.soapEndpoint.EmployeeEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class for managing employee-related operations.
 * This class handles requests related to employee management, such as fetching employee details and saving new employees.
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    /**
     * Call to the interface for managing employee-related operations.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Endpoint class for handling SOAP requests.
     */
    @Autowired
    private EmployeeEndpoint employeeEndpoint;

    /**
     * Retrieves the employee from a restful get method and if it has the correct values
     * it is saved through SOAP in MySQL database.
     *
     * @param employeeInput the input employee object containing the employee information
     * @return a ResponseEntity containing the response data, including employee details
     */
    @GetMapping("/employee/")
    public ResponseEntity<ResponseDTO<Optional<Employee>>> getEmployee(@RequestBody(required = true) Employee employeeInput) {
        ResponseDTO<Optional<Employee>> response = new ResponseDTO<>();
        try {
            if (areNullValues(employeeInput)) {
                response.setAnswer(false);
                response.setMsg("Null values are not allowed for an employee");
                response.setData(Optional.of(employeeInput));
            } else if (!isAValidDate(employeeInput.getDateOfBirth()) ||
                    !isAValidDate(employeeInput.getHiringDate())) {
                response.setAnswer(false);
                response.setMsg("Date of birth is not in the correct format yyyy-MM-dd");
            } else if (!isAnAdult(employeeInput.getDateOfBirth())) {
                response.setAnswer(false);
                response.setMsg("Employee must be older than " + ADULT_AGE);

            } else {
                employeeEndpoint.saveEmployee(employeeInput);

                response.setData(Optional.of(employeeInput));
                response.setEmployeeAge(getEmployeeDate(employeeInput.getDateOfBirth()));
                response.setWorkedTime(getEmployeeDate(employeeInput.getHiringDate()));
                response.setMsg("Successfully saved");
                response.setAnswer(true);
            }

        } catch (
                Exception e) {
            response.setAnswer(false);
            response.setMsg("Fail: " + e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
