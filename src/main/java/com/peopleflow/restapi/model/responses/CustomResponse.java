package com.peopleflow.restapi.model.responses;

public enum CustomResponse {

    SUCCESS("Success",0),
    UNABLE_TO_EXECUTE_REQUEST("Unable to execute request",-1);

    private final String message;
    private final int code;

    CustomResponse(String message,int code){
        this.message = message;
        this.code = code;
    }

    public int getCode(){
        return  this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
