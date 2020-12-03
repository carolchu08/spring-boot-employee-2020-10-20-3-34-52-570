package com.thoughtworks.springbootemployee.controller;

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

    /**
     * returns a view (not a new list) of the sourceList for the
     * range based on page and pageSize
     *
     * @param sourceList
     * @param page,      page number should start from 1
     * @param pageSize
     * @return custom error can be given instead of returning emptyList
     */
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

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeeWithSameGender(@RequestParam String gender) {
        return employees.stream().filter(employees -> employees.getGender().equals(gender)).collect(Collectors.toList());
    }

    @PostMapping
    public Employee createNewEmployee(@RequestBody Employee newEmployee) {
        employees.add(newEmployee);
        return newEmployee;
    }

    @PutMapping("/{employeeID}")
    public Employee updateEmployee(@PathVariable String employeeID, @RequestBody Employee employeeUpdate) {
        employees.stream().filter(employee -> employee.getEmployeeID().equals(employeeID))
                .findFirst()
                .ifPresent(employee -> {
                    employees.remove(employee);
                    employees.add(employeeUpdate);
                });
        return employeeUpdate;
    }

    @DeleteMapping("/{employeeID}")
    public Employee deleteEmployee(@PathVariable String employeeID, @RequestBody Employee deleteEmployee) {
        employees.stream().filter(employee -> employee.getEmployeeID().equals(employeeID))
                .findFirst()
                .ifPresent(employee -> {
                    employees.remove(employee);
                });
        return deleteEmployee;
    }


}
