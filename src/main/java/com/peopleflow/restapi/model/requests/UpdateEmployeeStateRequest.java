package com.peopleflow.restapi.model.requests;

import com.peopleflow.restapi.model.EmployeeState;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateEmployeeStateRequest {

    @NotBlank
    @NotNull
    private Long employeeId;

    @NotBlank
    @NotNull
    private EmployeeState employeeState;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeState getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(EmployeeState employeeState) {
        this.employeeState = employeeState;
    }
}
