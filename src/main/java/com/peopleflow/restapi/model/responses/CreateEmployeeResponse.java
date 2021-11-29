package com.peopleflow.restapi.model.responses;

import com.peopleflow.restapi.model.Employee;

public class CreateEmployeeResponse extends Response {

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
