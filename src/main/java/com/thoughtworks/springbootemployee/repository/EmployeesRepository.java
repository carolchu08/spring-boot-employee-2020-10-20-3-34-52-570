package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeesRepository {
    List<Employee> employees = new ArrayList<>();

    public List<Employee> findAllEmployees() {
        return this.employees;
    }

    public Employee findOneEmployee(String employeeID) {
        return employees.stream().filter(employees -> employees.getEmployeeID().equals(employeeID)).findFirst().orElse(null);
    }

    public Employee createEmployee(Employee newEmployee) {
        employees.add(newEmployee);
        return newEmployee;
    }

    public Employee updateEmployee(String employeeID, Employee updateEmployee) {
        employees.stream().filter(employee -> employee.getEmployeeID().equals(employeeID))
                .findFirst()
                .ifPresent(employee -> {
                    employees.remove(employee);
                    employees.add(updateEmployee);
                });
        return updateEmployee;
    }

    public Employee deleteEmployee(String employeeID, Employee deleteEmployee) {
        employees.stream().filter(employee -> employee.getEmployeeID().equals(employeeID))
                .findFirst()
                .ifPresent(employee -> {
                    employees.remove(employee);
                });
        return deleteEmployee;
    }
}
