package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private List<Company> company = new ArrayList<>();
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAll() {
        return companyService.getAll();
    }

    @GetMapping("{companyID}")
    public Company getSpecificCompany(@PathVariable String companyID){
        return companyService.getSpecificCompany(companyID);
    }
}
