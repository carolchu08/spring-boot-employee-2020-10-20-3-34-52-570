package com.thoughtworks.springbootemployee.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
@Document
public class Company {
    @MongoId(FieldType.OBJECT_ID)
    private String companyID;
    private String companyName;
    private Integer employeeNum;

    public Company(String companyName, String companyID, Integer employeeNum) {
        this.companyName = companyName;
        this.employeeNum = employeeNum;
        this.companyID = companyID;
    }
    public Company(String companyName, Integer employeeNum) {
        this.companyName = companyName;
        this.employeeNum = employeeNum;
    }

    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getEmployeeNum() {
        return employeeNum;
    }


    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployeeNum(Integer employeeNum) {
        this.employeeNum = employeeNum;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
