package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeesService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeesService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public Employee getOneEmployee(String employeeID) throws EmployeeNotFoundException {
        return this.employeeRepository.findById(employeeID).orElseThrow(EmployeeNotFoundException::new);

    }

    public List<Employee> getPagination(int page, int pageSize) {
        return employeeRepository.findAll().stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    public Employee createEmployee(Employee newEmployee) {
        return this.employeeRepository.save(newEmployee);
    }

    public List<Employee> getEmployeeWithSameGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Employee updateEmployee(String employeeID, Employee updateEmployee) {
        Employee originalEmployee = employeeRepository.findById(employeeID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));
        originalEmployee.setAge(updateEmployee.getAge());
        originalEmployee.setEmployeeName(updateEmployee.getEmployeeName());
        originalEmployee.setGender(updateEmployee.getGender());
        originalEmployee.setSalary(updateEmployee.getSalary());
        originalEmployee.setCompanyID(updateEmployee.getCompanyID());
        return this.employeeRepository.save(originalEmployee);
    }

    public void deleteEmployee(String employeeID) throws EmployeeNotFoundException {
        this.getOneEmployee(employeeID);
        this.employeeRepository.deleteById(employeeID);
    }

    public List<Employee> getEmployeeWithSameCompanyID(String companyID) {
        return employeeRepository.findAllByCompanyID(companyID);
    }
}
