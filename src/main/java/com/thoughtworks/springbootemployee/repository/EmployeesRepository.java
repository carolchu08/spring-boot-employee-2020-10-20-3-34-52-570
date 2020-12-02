package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class EmployeesRepository {
      List<Employees> employees = new ArrayList<>();

    public List<Employees> findAllEmployees (){
        return this.employees;
    }
    public Employees findOneEmployee(String employeeID) {
        return employees.stream().filter(employees -> employees.getEmployeeID().equals(employeeID)).findFirst().orElse(null);
    }

    public Employees createEmployee (Employees newEmployee){
        employees.add(newEmployee);
        return newEmployee;
    }
    public Employees updateEmployee (String employeeID, Employees updateEmployee){
        employees.stream().filter(employee -> employee.getEmployeeID().equals(employeeID))
                .findFirst()
                .ifPresent(employee -> {
                    employees.remove(employee);
                    employees.add(updateEmployee);
                });
        return updateEmployee;
    }
}
