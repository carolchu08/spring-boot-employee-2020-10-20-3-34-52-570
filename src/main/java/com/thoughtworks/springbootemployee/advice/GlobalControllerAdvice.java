package com.thoughtworks.springbootemployee.advice;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({IllegalArgumentException.class})
    public ErrorResponse handleBadRequest(IllegalArgumentException exception){
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.name());

    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({CompanyNotFoundException.class})
    public ErrorResponse handleCompanyNotFound(ResponseStatusException exception){
        return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.name());

    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EmployeeNotFoundException.class})
    public ErrorResponse handleEmployeeNotFound(ResponseStatusException exception){
        return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.name());

    }
}
