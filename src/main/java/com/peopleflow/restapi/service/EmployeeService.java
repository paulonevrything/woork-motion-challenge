package com.peopleflow.restapi.service;

import com.peopleflow.restapi.model.requests.*;
import com.peopleflow.restapi.model.responses.CreateEmployeeResponse;
import com.peopleflow.restapi.model.responses.Response;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    public ResponseEntity<CreateEmployeeResponse> createEmployee(CreateEmployeeRequest request);

    public ResponseEntity<Response> updateEmployeeState(UpdateEmployeeStateRequest request);

}
