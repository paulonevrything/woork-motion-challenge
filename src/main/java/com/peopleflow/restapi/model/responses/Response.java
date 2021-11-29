package com.peopleflow.restapi.model.responses;

public class Response {

    private Integer responseCode;

    private String message;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response(){}

    public void setResponse(CustomResponse response){
        this.message = response.getMessage();
        this.responseCode = response.getCode();
    }
}
