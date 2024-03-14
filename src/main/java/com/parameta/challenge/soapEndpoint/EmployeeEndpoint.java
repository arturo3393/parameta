package com.parameta.challenge.soapEndpoint;

import com.parameta.challenge.domain.Employee;
import com.parameta.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Endpoint class for handling SOAP requests related to Employee entities.
 */
@Endpoint
@Component
public class EmployeeEndpoint {
    /**
     * The namespace URI for SOAP requests related to Employee entities.
     */
    private static final String NAMESPACE_URI = "http://localhost:8081/api/v1/employee";

    /**
     * The service responsible for managing Employee entities.
     */
    private EmployeeService employeeService;

    /**
     * Constructs a new instance of EmployeeEndpoint with the specified EmployeeService.
     *
     * @param employeeService The service responsible for managing Employee entities.
     */
    @Autowired
    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Handles SOAP requests to save an Employee entity.
     *
     * @param employee The Employee entity to save.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Employee")
    @ResponsePayload
    public void saveEmployee(@RequestPayload Employee employee) {
        employeeService.save(employee);
    }
}
