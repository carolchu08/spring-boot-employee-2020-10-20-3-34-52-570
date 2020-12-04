package com.thoughtworks.springbootemployee.dto;

import java.util.List;

public class CompanyResponse {
    private String companyName;
    private String companyID;
    private List<EmployeeResponse> employeeResponses;
    private Integer employeeNum = 0;

    public CompanyResponse(String companyName, String companyID, List<EmployeeResponse> employeeResponses) {
        this.companyName = companyName;
        this.companyID = companyID;
        this.employeeResponses = employeeResponses;
        this.employeeNum = employeeResponses.size();
    }

    public CompanyResponse() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public List<EmployeeResponse> getEmployeeResponses() {
        return employeeResponses;
    }

    public void setEmployeeResponses(List<EmployeeResponse> employeeResponses) {
        this.employeeResponses = employeeResponses;
    }

    public Integer getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(Integer employeeNum) {
        this.employeeNum = employeeNum;
    }
}
