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


}
