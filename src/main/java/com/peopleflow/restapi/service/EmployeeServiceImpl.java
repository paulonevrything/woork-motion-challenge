package com.peopleflow.restapi.service;

import com.peopleflow.restapi.exceptions.EmployeeNotFoundException;
import com.peopleflow.restapi.exceptions.StateUpdateException;
import com.peopleflow.restapi.model.Employee;
import com.peopleflow.restapi.model.EmployeeState;
import com.peopleflow.restapi.model.requests.UpdateEmployeeStateRequest;
import com.peopleflow.restapi.model.responses.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.peopleflow.restapi.model.requests.CreateEmployeeRequest;
import com.peopleflow.restapi.model.responses.CreateEmployeeResponse;
import com.peopleflow.restapi.model.responses.CustomResponse;
import com.peopleflow.restapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Log logger = LogFactory.getLog(EmployeeServiceImpl.class);

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){

        this.employeeRepository = employeeRepository;
    }


    @Override
    public ResponseEntity<CreateEmployeeResponse> createEmployee(CreateEmployeeRequest request) {

        CreateEmployeeResponse response = new CreateEmployeeResponse();

        try {

            Employee newEmployee = new Employee(request);
            newEmployee.setEmployeeState(EmployeeState.ADDED.toString());

            Employee result = this.employeeRepository.save(newEmployee);

            response.setResponse(CustomResponse.SUCCESS);
            response.setEmployee(result);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception ex) {

            logger.error(ex.getMessage());

            response.setMessage(ex.getMessage());
            response.setResponseCode(CustomResponse.UNABLE_TO_EXECUTE_REQUEST.getCode());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Response> updateEmployeeState(UpdateEmployeeStateRequest request, Long employeeId) {

        CreateEmployeeResponse response = new CreateEmployeeResponse();

        try {

            // verify employee exist
            Employee employeeToUpdate = getEmployeeById(employeeId);

            // verify state change is valid
            EmployeeState employeeState = EmployeeState.enumValue(employeeToUpdate.getEmployeeState());
            EmployeeState requestedState = EmployeeState.enumValue(request.getEmployeeState());

            if(employeeState == null || requestedState == null || !stateChangeIsValid(employeeState, requestedState)) {

                throw new StateUpdateException();
            }

            employeeToUpdate.setEmployeeState(request.getEmployeeState());
            employeeRepository.save(employeeToUpdate);

            response.setResponse(CustomResponse.SUCCESS);
            response.setEmployee(employeeToUpdate);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception exception) {

            logger.error(exception.getMessage());

            if(exception instanceof StateUpdateException) {

                response.setMessage(exception.getMessage());
                response.setResponseCode(CustomResponse.UNABLE_TO_EXECUTE_REQUEST.getCode());
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);

            }else if(exception instanceof EmployeeNotFoundException) {

                response.setMessage(exception.getMessage());
                response.setResponseCode(CustomResponse.UNABLE_TO_EXECUTE_REQUEST.getCode());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }


            response.setMessage(exception.getMessage());
            response.setResponseCode(CustomResponse.UNABLE_TO_EXECUTE_REQUEST.getCode());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Employee getEmployeeById(Long employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException();
        }

        return employee.get();
    }

    private boolean stateChangeIsValid(EmployeeState presentState, EmployeeState requestedChange) {

        return presentState.stringencyOrder() < requestedChange.stringencyOrder();
    }
}
