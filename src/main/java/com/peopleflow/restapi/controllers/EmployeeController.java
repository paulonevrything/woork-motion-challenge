package com.peopleflow.restapi.controllers;

import com.peopleflow.restapi.model.requests.CreateEmployeeRequest;
import com.peopleflow.restapi.model.responses.CreateEmployeeResponse;
import com.peopleflow.restapi.service.EmployeeService;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/employee", consumes = "application/json", produces = "application/json")

public class EmployeeController {


    @Autowired
    EmployeeService employeeService;


    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "An internal error occurred"),
            @io.swagger.annotations.ApiResponse(code = 201, message = "Successfully created")
    })
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateEmployeeResponse> getWordFrequencyAndSimilarWords(@RequestBody @Valid CreateEmployeeRequest request) {

        return this.employeeService.createEmployee(request);
    }
}
