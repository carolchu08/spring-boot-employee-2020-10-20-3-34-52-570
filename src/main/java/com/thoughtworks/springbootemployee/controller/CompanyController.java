package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.mapping.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyMapper companyMapper;

    @GetMapping
    public List<CompanyResponse> getAll() {
        return companyService.getAll().stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{companyID}")
    public CompanyResponse getSpecificCompany(@PathVariable String companyID) throws CompanyNotFoundException {
        return companyMapper.toResponse(companyService.getSpecificCompany(companyID));
    }

    @GetMapping("/{companyID}/employees")
    public List<Employee> getEmployeesWithSpecificCompany(@PathVariable String companyID) {
        return companyService.getEmployeesWithSpecificCompany(companyID);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<CompanyResponse> getAllCompany(@RequestParam int page, @RequestParam int pageSize) {
        return companyService.getAllCompanyWithPage(page, pageSize).stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(@RequestBody CompanyRequest updateCompany) {
        return companyMapper.toResponse(companyService.createCompany(companyMapper.toEntity(updateCompany)));
    }

    @PutMapping("/{companyID}")
    public CompanyResponse updateCompany(@PathVariable String companyID, @RequestBody Company company) throws CompanyNotFoundException {
        return companyMapper.toResponse(companyService.updateCompany(companyID, company));
    }

    @DeleteMapping("/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable String companyID) throws CompanyNotFoundException {
        companyService.deleteCompany(companyID);
    }

}
