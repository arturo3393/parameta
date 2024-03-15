package com.parameta.challenge.controller;

import static com.parameta.challenge.utils.Constants.ADULT_AGE;
import static com.parameta.challenge.utils.EmployeeUtils.*;

import com.parameta.challenge.domain.Employee;
import com.parameta.challenge.domain.ResponseDTO;
import com.parameta.challenge.service.EmployeeService;
import com.parameta.challenge.soapEndpoint.EmployeeEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
     * Retrieves employee details based on provided parameters.
     *
     * @param id           The unique identifier of the employee. (Required)
     * @param name         The first name of the employee. (Required)
     * @param lastName     The last name of the employee. (Required)
     * @param dateOfBirth  The date of birth of the employee in yyyy-MM-dd format. (Required)
     * @param hiringDate   The hiring date of the employee in yyyy-MM-dd format. (Required)
     * @param jobTitle     The job title of the employee. (Required)
     * @param salary       The salary of the employee. (Required)
     * @return             ResponseEntity containing ResponseDTO with employee details.
     *                     If successful, ResponseDTO will contain employee details along with a success message.
     *                     If unsuccessful, ResponseDTO will contain an error message.
     */
    @GetMapping("/employee/")
    public ResponseEntity<ResponseDTO<Optional<Employee>>> getEmployee(@RequestParam(required = true) String id,
                                                                         @RequestParam(required = true) String name,
                                                                         @RequestParam(required = true) String lastName,
                                                                         @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth,
                                                                         @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate hiringDate,
                                                                         @RequestParam(required = true) String jobTitle,
                                                                         @RequestParam(required = true) Double salary) {
        ResponseDTO<Optional<Employee>> response = new ResponseDTO<>();
        Employee employeeInput = new Employee(id,name,lastName,dateOfBirth,hiringDate,jobTitle,salary);
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
