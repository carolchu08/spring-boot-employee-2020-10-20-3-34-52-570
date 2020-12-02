package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesService {
    @Autowired private EmployeesRepository employeesRepository;

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
        return getPage(employeesRepository.findAllEmployees(),page,pageSize);
    }
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() <= fromIndex) {
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }
    public Employees createEmployee(Employees newEmployee){
        return employeesRepository.createEmployee(newEmployee);
    }
    public List <Employees> getEmployeeWithSameGender(String gender){
        return employeesRepository.findAllEmployees().stream().filter(employees -> employees.getGender().equals(gender)).collect(Collectors.toList());
    }
}
