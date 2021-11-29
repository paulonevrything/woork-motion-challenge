package com.peopleflow.restapi.service;

import com.peopleflow.restapi.model.Employee;
import com.peopleflow.restapi.model.requests.CreateEmployeeRequest;
import com.peopleflow.restapi.model.responses.CreateEmployeeResponse;
import com.peopleflow.restapi.model.responses.CustomResponse;
import com.peopleflow.restapi.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService{


    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){

        this.employeeRepository = employeeRepository;
    }


    @Override
    public ResponseEntity<CreateEmployeeResponse> createEmployee(CreateEmployeeRequest request) {

        CreateEmployeeResponse response = new CreateEmployeeResponse();

        try {

            Employee newEmployee = new Employee(request);

            Employee result = this.employeeRepository.save(newEmployee);

            response.setResponse(CustomResponse.SUCCESS);
            response.setEmployee(result);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception ex) {

            response.setMessage(ex.getMessage());
            response.setResponseCode(CustomResponse.UNABLE_TO_EXECUTE_REQUEST.getCode());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
