package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {
    private String companyName;
    private  Integer employeeNum;
    private List<Employees> employees;

    public Company(String companyName, Integer employeeNum, List<Employees> employees) {
        this.companyName = companyName;
        this.employeeNum = employeeNum;
        this.employees = employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getEmployeeNum() {
        return employeeNum;
    }

    public List<Employees> getEmployees() {
        return employees;
    }
}
