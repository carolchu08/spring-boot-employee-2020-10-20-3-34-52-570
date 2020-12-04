package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyResult;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<CompanyResult> getAll() {
        return companyRepository.findAll().stream().map(company -> new CompanyResult(company.getCompanyName(), company.getCompanyID(), this.getEmployeesWithSpecificCompany(company.getCompanyID()))).collect(Collectors.toList());
    }

    public CompanyResult getSpecificCompany(String companyID) {
        Company company = companyRepository.findById(companyID).orElseThrow(RuntimeException::new);
       return new CompanyResult(company.getCompanyName(),company.getCompanyID(),this.getEmployeesWithSpecificCompany(company.getCompanyID()));

    }

    public List<Employee> getEmployeesWithSpecificCompany(String companyID) {

            return employeeRepository.findAllByCompanyID(companyID);
    }

    public List<CompanyResult> getAllCompanyWithPage(int page, int pageSize) {
        return getAll().stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }


    public Company createCompany(Company updateCompany) {
        return companyRepository.save(updateCompany);
    }

    public Company updateCompany(String companyID, Company updateCompanyInfo) throws CompanyNotFoundException {
        companyRepository.findById(companyID).orElseThrow(CompanyNotFoundException::new);
            updateCompanyInfo.setCompanyID(companyID);
            return companyRepository.save(updateCompanyInfo);

    }

    public void deleteCompany(String companyID) throws CompanyNotFoundException {
        Company originalCompany = companyRepository.findById(companyID).orElseThrow(CompanyNotFoundException::new);
            companyRepository.deleteById(companyID);

    }
}
