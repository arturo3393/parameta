package com.parameta.challenge.soapEndpoint;

import com.parameta.challenge.domain.Employee;
import com.parameta.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Component
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8081/api/v1/employee";

    private EmployeeService employeeService;

    @Autowired
    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Employee")
    @ResponsePayload
    public void saveEmployee(@RequestPayload Employee employee) {
        employeeService.save(employee);
    }
}
