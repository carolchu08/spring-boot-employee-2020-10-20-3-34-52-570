package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesService {
    @Autowired
    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<Employee> getAllEmployees() {
        return this.employeesRepository.findAllEmployees();
    }

    public Employee getOneEmployee(String employeeID) {
        return this.employeesRepository.findOneEmployee(employeeID);
    }

    public List<Employee> getPagination(int page, int pageSize) {
        return employeesRepository.findAllEmployees().stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    public Employee createEmployee(Employee newEmployee) {
        return this.employeesRepository.createEmployee(newEmployee);
    }

    public List<Employee> getEmployeeWithSameGender(String gender) {
        return this.employeesRepository.findAllEmployees().stream().filter(employees -> employees.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee updateEmployee(String employeeID, Employee updateEmployee) {
        return this.employeesRepository.updateEmployee(employeeID, updateEmployee);
    }

    public Employee deleteEmployee(String employeeID, Employee deleteEmployee) {
        return this.employeesRepository.deleteEmployee(employeeID, deleteEmployee);
    }
}
