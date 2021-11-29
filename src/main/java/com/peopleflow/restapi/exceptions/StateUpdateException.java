package com.peopleflow.restapi.exceptions;

public class StateUpdateException extends RuntimeException{

    public StateUpdateException() {
        super("The requested state change is not valid. New state must be higher than previous state");
    }
}
