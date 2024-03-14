package com.parameta.challenge.controller;

import com.parameta.challenge.domain.Employee;
import com.parameta.challenge.domain.ResponseDTO;
import com.parameta.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

import static com.parameta.challenge.utils.Constants.ADULT_AGE;
import static com.parameta.challenge.utils.EmployeeUtils.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/")
    public ResponseEntity<ResponseDTO<Optional<Employee>>> getEmployeeById(@RequestBody(required = true) Employee employeeInput) {
        ResponseDTO<Optional<Employee>> response = new ResponseDTO<>();
        try {
            if (areNullValues(employeeInput)) {
                response.setAnswer(false);
                response.setMsg("Null values are not allowed for an employee");
                response.setData(Optional.of(employeeInput));
            } else if (!isAValidDate(employeeInput.dateOfBirth()) ||
                    !isAValidDate(employeeInput.hiringDate())) {
                response.setAnswer(false);
                response.setMsg("Date of birth is not in the correct format yyyy-MM-dd");
            } else if (!isAnAdult(employeeInput.dateOfBirth())) {
                response.setAnswer(false);
                response.setMsg("Employee must be older than " + ADULT_AGE);

            } else {
                Optional<Employee> employee = employeeService.findEmployeeById(employeeInput.id());
                if (employee.isPresent()) {
                    // TODO: Invoke soap method


                    response.setData(employee);
                    response.setEmployeeAge(getEmployeeDate(employeeInput.dateOfBirth()));
                    response.setWorkedTime(getEmployeeDate(employeeInput.hiringDate()));
                    response.setMsg("Successfully retrieved");
                    response.setAnswer(true);
                } else {
                    response.setMsg("The employee " + employee + " is not in the data base");
                    response.setAnswer(true);
                }

            }


        } catch (Exception e) {
            response.setAnswer(false);
            response.setMsg("Fail: " + e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
