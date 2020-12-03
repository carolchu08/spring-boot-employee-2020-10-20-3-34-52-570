package com.thoughtworks.springbootemployee.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Employee {
    @MongoId(FieldType.OBJECT_ID)
    private String employeeID;
    private String employeeName;
    private Integer age;
    private Integer salary;
    private String gender;
    private String companyID;

    public Employee() {
    }

    public Employee(String employeeName, String employeeID, Integer age, String gender, Integer salary,String companyID) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyID = companyID;
    }
    public Employee(String employeeName, Integer age, String gender, Integer salary, String companyID) {
        this.employeeName = employeeName;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyID = companyID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getGender() {
        return gender;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
