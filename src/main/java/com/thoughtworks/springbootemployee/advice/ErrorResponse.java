package com.thoughtworks.springbootemployee.advice;

public class ErrorResponse {
    private  String message;
    private  String status;


    public ErrorResponse(String message, String status) {
        this.message= message;
        this.status = status;

    }
}
