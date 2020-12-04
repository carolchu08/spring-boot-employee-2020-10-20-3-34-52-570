package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyResult;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private List<Company> company = new ArrayList<>();
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<CompanyResult> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/{companyID}")
    public CompanyResult getSpecificCompany(@PathVariable String companyID) {
        return companyService.getSpecificCompany(companyID);
    }

    @GetMapping("/{companyID}/employees")
    public List<Employee> getEmployeesWithSpecificCompany(@PathVariable String companyID) {
        return companyService.getEmployeesWithSpecificCompany(companyID);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<CompanyResult> getAllCompany(@RequestParam int page, @RequestParam int pageSize) {
        return companyService.getAllCompanyWithPage(page, pageSize);
    }

    @PostMapping
    public Company createCompany(@RequestBody Company updateCompany) {
        return companyService.createCompany(updateCompany);
    }
    @PutMapping("/{companyID}")
    public Company updateCompany(@PathVariable String companyID, @RequestBody Company company) throws CompanyNotFoundException {
        return companyService.updateCompany(companyID,company);
    }

    @DeleteMapping("/{companyID}")@ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable String companyID) throws CompanyNotFoundException {
        companyService.deleteCompany(companyID);
    }

}
