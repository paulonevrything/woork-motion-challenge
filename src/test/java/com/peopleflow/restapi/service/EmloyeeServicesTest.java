package com.peopleflow.restapi.service;


import com.peopleflow.restapi.model.Employee;
import com.peopleflow.restapi.model.requests.CreateEmployeeRequest;
import com.peopleflow.restapi.model.requests.UpdateEmployeeStateRequest;
import static org.assertj.core.api.Assertions.assertThat;

import com.peopleflow.restapi.model.responses.Response;
import com.peopleflow.restapi.repository.EmployeeRepository;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmloyeeServicesTest {

    private EmployeeService employeeService = mock(EmployeeService.class);

    private EmployeeRepository employeeRepository = mock(EmployeeRepository.class);


    @Test
    @Description("This should test a a failed state update due to employee not found")
    public void shouldReturnNotFoundStatus() {

        UpdateEmployeeStateRequest request = new UpdateEmployeeStateRequest();

        request.setEmployeeState("APPROVED");

        ResponseEntity<Response> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        when(employeeRepository.findById(34L)).thenReturn(null);

        when(employeeService.updateEmployeeState(request, 34L)).thenReturn(response);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    @Description("This should test a failed state update due to invalid state passed")
    public void shouldReturnBadRequestStatus() {

        UpdateEmployeeStateRequest request = new UpdateEmployeeStateRequest();

        request.setEmployeeState("IN-CHECK");

        ResponseEntity<Response> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Employee existingEmployee = new Employee();
        existingEmployee.setEmployeeId(34L);
        existingEmployee.setEmployeeState("APPROVED");

        when(employeeRepository.findById(34L)).thenReturn(null);

        when(employeeService.updateEmployeeState(request, 34L)).thenReturn(response);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }


    @Test
    @Description("This should test a successful state change")
    public void shouldReturnAnOKStatus() {

        UpdateEmployeeStateRequest request = new UpdateEmployeeStateRequest();

        request.setEmployeeState("IN-CHECK");

        ResponseEntity<Response> response = new ResponseEntity<>(HttpStatus.OK);

        Employee existingEmployee = new Employee();
        existingEmployee.setEmployeeId(34L);
        existingEmployee.setEmployeeState("ADDED");

        when(employeeRepository.findById(34L)).thenReturn(null);

        when(employeeService.updateEmployeeState(request, 34L)).thenReturn(response);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}
