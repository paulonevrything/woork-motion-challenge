package com.peopleflow.restapi.service;

import com.peopleflow.restapi.model.requests.CreateEmployeeRequest;
import com.peopleflow.restapi.model.responses.CreateEmployeeResponse;
import org.springframework.http.ResponseEntity;

public interface IEmployeeService {

    public ResponseEntity<CreateEmployeeResponse> createEmployee(CreateEmployeeRequest request);

}
