package com.thoughtworks.springbootemployee.dto;

public class EmployeeRequest {
    private String employeeName;
    private Integer age;
    private String gender;
    private Integer salary;
    private String companyID;

    public EmployeeRequest() {
    }

    public EmployeeRequest(String employeeName, Integer age, Integer salary,String gender,String companyID) {
        this.employeeName = employeeName;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyID =companyID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
