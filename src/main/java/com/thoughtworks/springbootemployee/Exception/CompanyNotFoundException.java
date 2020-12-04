package com.thoughtworks.springbootemployee.Exception;

public class CompanyNotFoundException extends Exception{
    public CompanyNotFoundException() {
        super("Company Not Found");
    }
}
