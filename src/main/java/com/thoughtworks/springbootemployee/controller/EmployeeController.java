package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapping.EmployeeMapper;
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
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return employeesService.getAllEmployees().stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{employeeID}")
    public EmployeeResponse getOneEmployee(@PathVariable String employeeID) {
        return employeeMapper.toResponse(employeesService.getOneEmployee(employeeID));

    }

    @GetMapping(params = {"page", "pageSize"})
    public List<EmployeeResponse> getAllEmployeeWithPage(@RequestParam int page, @RequestParam int pageSize) {
        return employeesService.getPagination(page, pageSize).stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping(params = {"gender"})
    public List<EmployeeResponse> getEmployeeWithSameGender(@RequestParam String gender) {
        return employeesService.getEmployeeWithSameGender(gender).stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    public EmployeeResponse createNewEmployee(@RequestBody EmployeeRequest employeeRequest) {
       Employee employee = employeesService.createEmployee(employeeMapper.toEntity(employeeRequest));
       return employeeMapper.toResponse(employee);
    }

    @PutMapping("/{employeeID}")
    public EmployeeResponse updateEmployee(@PathVariable String employeeID, @RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeesService.updateEmployee(employeeID,employeeMapper.toEntity(employeeRequest)));
    }

    @DeleteMapping("/{employeeID}")
    public void deleteEmployee(@PathVariable String employeeID) throws EmployeeNotFoundException {
        employeesService.deleteEmployee(employeeID);
    }

}
