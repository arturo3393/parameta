package com.parameta.challenge.controller;

import static org.junit.jupiter.api.Assertions.*;


import com.parameta.challenge.domain.Employee;
import com.parameta.challenge.domain.ResponseDTO;
import com.parameta.challenge.service.EmployeeService;
import com.parameta.challenge.soapEndpoint.EmployeeEndpoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private EmployeeEndpoint employeeEndpoint;

    @InjectMocks
    private EmployeeController employeeController;


    @Test
    void testWhenRestMethodHasNullValues() {
        Employee employee = new Employee("1", null, null,
                null, null, "developer", 2D);

        ResponseDTO<Optional<Employee>> expectedResponse = new ResponseDTO<>();
        expectedResponse.setAnswer(false);
        expectedResponse.setMsg("Null values are not allowed for an employee");
        expectedResponse.setData(Optional.of(employee));

        assertEquals(new ResponseEntity<>(expectedResponse, HttpStatus.OK), employeeController.getEmployee("1", null, null,
                null, null, "developer", 2D));

    }



    @Test
    void testWhenEmployeeIsNotAnAdult() {
        Employee employee = new Employee("1", "Pablo", "Beltran",
                LocalDate.parse("2020-03-14"), LocalDate.now(), "developer", 2D);

        ResponseDTO<Optional<Employee>> expectedResponse = new ResponseDTO<>();
        expectedResponse.setAnswer(false);
        expectedResponse.setMsg("Employee must be older than 18");

        assertEquals(new ResponseEntity<>(expectedResponse, HttpStatus.OK), employeeController.getEmployee("1", "Pablo", "Beltran",
                LocalDate.parse("2020-03-14"), LocalDate.now(), "developer", 2D));

    }

    @Test
    void testWhenEmployeeShouldBeSaved() {
        Employee employee = new Employee("1", "Pablo", "Beltran",
                LocalDate.parse("1988-03-14"), LocalDate.now(), "developer", 2D);

        ResponseDTO<Optional<Employee>> expectedResponse = new ResponseDTO<>();
        expectedResponse.setAnswer(true);
        expectedResponse.setMsg("Successfully saved");
        expectedResponse.setData(Optional.of(employee));

        assertEquals(Objects.requireNonNull(new ResponseEntity<>(expectedResponse, HttpStatus.OK).getBody()).getMsg(),
                "Successfully saved");
    }

}