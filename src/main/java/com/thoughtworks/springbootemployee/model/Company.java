package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {
    private final String companyName;
    private final Integer employeeNum;
    private final List<Employees> employees;
    private final String companyID;

    public Company(String companyName,String companyID, Integer employeeNum, List<Employees> employees) {
        this.companyName = companyName;
        this.employeeNum = employeeNum;
        this.employees = employees;
        this.companyID = companyID;
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

    public String getCompanyID() {
        return companyID;
    }
}
