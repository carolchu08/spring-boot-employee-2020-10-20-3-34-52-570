package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class EmployeesService {
    @Autowired
    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<Employees> getAll() {
        return employeesRepository.findAllEmployees();
    }
    public Employees getOneEmployee(String employeeID) {
        return employeesRepository.findOneEmployee(employeeID);
    }
    public List<Employees> getPagination(int page, int pageSize){
        return employeesRepository.getPagination(page,pageSize);
    }
}
