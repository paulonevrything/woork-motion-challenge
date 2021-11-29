package com.peopleflow.restapi.model.requests;

import com.peopleflow.restapi.model.EmployeeState;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateEmployeeStateRequest {

    @NotBlank
    @NotNull
    private String employeeState;

    public String getEmployeeState() {

        return this.employeeState;
    }

    public void setEmployeeState(String employeeState) {
        this.employeeState = toString();
    }
}
