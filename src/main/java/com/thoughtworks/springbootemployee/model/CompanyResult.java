package com.thoughtworks.springbootemployee.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
@Document
public class CompanyResult {
    private String companyName;
    @MongoId(FieldType.OBJECT_ID)
    private String companyID;
    private Integer employeeNum;
    private List<Employee> employeeList;

    public CompanyResult(String companyName, String companyID, List<Employee> employeeList) {
        this.companyName = companyName;
        this.companyID = companyID;
        this.employeeList = employeeList;
        this.employeeNum = employeeList.size();
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

    public Integer getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(Integer employeeNum) {
        this.employeeNum = employeeNum;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
