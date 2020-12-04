package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();
    @Autowired
    private EmployeesService employeesService;

    @GetMapping
    public List<Employee> getAll() {
        return employeesService.getAllEmployees();
    }

    @GetMapping("/{employeeID}")
    public Employee getOneEmployee(@PathVariable String employeeID) {
        return employeesService.getOneEmployee(employeeID);

    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getAllEmployeeWithPage(@RequestParam int page, @RequestParam int pageSize) {
        //employees.add(new Employees("pater","123",12,1000,"male"));
        return employeesService.getPagination(page, pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeeWithSameGender(@RequestParam String gender) {
        return employeesService.getEmployeeWithSameGender(gender);
    }

    @PostMapping
    public Employee createNewEmployee(@RequestBody Employee newEmployee) {
       return employeesService.createEmployee(newEmployee);
    }

    @PutMapping("/{employeeID}")
    public Employee updateEmployee(@PathVariable String employeeID, @RequestBody Employee employeeUpdate) {
        return employeesService.updateEmployee(employeeID,employeeUpdate);
    }

    @DeleteMapping("/{employeeID}")
    public void deleteEmployee(@PathVariable String employeeID) throws EmployeeNotFoundException {
        employeesService.deleteEmployee(employeeID);
    }

}
