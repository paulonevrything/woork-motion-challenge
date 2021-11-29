package com.peopleflow.restapi.exceptions;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException() {
        super("Employee is not found");
    }
}
