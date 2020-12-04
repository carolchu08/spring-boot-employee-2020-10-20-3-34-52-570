package com.thoughtworks.springbootemployee.Exception;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException() {
        super("Employee Not Found");
    }
}
