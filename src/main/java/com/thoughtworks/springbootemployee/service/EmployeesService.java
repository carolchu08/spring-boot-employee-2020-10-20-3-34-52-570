package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeesService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAllEmployees();
    }

    public Employee getOneEmployee(String employeeID) {
        return this.employeeRepository.findOneEmployee(employeeID);
    }

    public List<Employee> getPagination(int page, int pageSize) {
        return employeeRepository.findAllEmployees().stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    public Employee createEmployee(Employee newEmployee) {
        return this.employeeRepository.createEmployee(newEmployee);
    }

    public List<Employee> getEmployeeWithSameGender(String gender) {
        return this.employeeRepository.findAllEmployees().stream().filter(employees -> employees.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee updateEmployee(String employeeID, Employee updateEmployee) {
        return this.employeeRepository.updateEmployee(employeeID, updateEmployee);
    }

    public Employee deleteEmployee(String employeeID, Employee deleteEmployee) {
        return this.employeeRepository.deleteEmployee(employeeID, deleteEmployee);
    }
}
