package com.thoughtworks.springbootemployee.model;

public class Employee {
    private String employeeName;
    private String employeeID;
    private Integer age;
    private Integer salary;
    private String gender;

    public Employee() {
    }

    public Employee(String employeeName, String employeeID, Integer age, String gender, Integer salary) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
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
}
