package com.thoughtworks.springbootemployee.controller;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employees> employees = new ArrayList<>();

    @GetMapping
    public  List<Employees> getAll(){
        return employees;
    }

    @GetMapping("/{employeeID}")
    public Employees getOneEmployee(@PathVariable String employeeID){
        return employees.stream().filter(employees->employees.getEmployeeID().equals(employeeID)).findFirst().orElse(null);

    }
    @GetMapping(params={"page","pageSize"})
    public List<Employees> getAllEmployee(@RequestParam int page, @RequestParam int pageSize){
        //employees.add(new Employees("pater","123",12,1000,"male"));
        return getPage(employees,page,pageSize);
    }

    /**
     * returns a view (not a new list) of the sourceList for the
     * range based on page and pageSize
     * @param sourceList
     * @param page, page number should start from 1
     * @param pageSize
     * @return
     * custom error can be given instead of returning emptyList
     */
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if(pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if(sourceList == null || sourceList.size() <= fromIndex){
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    @GetMapping(params={"gender"})
    public List<Employees> getEmployeeWithSameGender(@RequestParam String gender){
        return employees.stream().filter(employees->employees.getGender().equals(gender)).collect(Collectors.toList());
    }

    @PostMapping
    public Employees createNewEmployee( @RequestBody Employees newEmployee){
        employees.add(newEmployee);
        return newEmployee;
    }

}
