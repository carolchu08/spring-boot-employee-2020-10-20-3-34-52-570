package com.thoughtworks.springbootemployee.dto;

public class EmployeeRequest {
    private String employeeName;
    private Integer Age;
    private String Gender;
    private Integer Salary;

    public EmployeeRequest() {
    }

    public EmployeeRequest(String employeeName, Integer age, String gender, Integer salary) {
        this.employeeName = employeeName;
        Age = age;
        Gender = gender;
        Salary = salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Integer getSalary() {
        return Salary;
    }

    public void setSalary(Integer salary) {
        Salary = salary;
    }
}
