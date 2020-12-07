package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getSpecificCompany(String companyID) throws CompanyNotFoundException {
        return companyRepository.findById(companyID).orElseThrow(CompanyNotFoundException::new);

    }

    public List<Employee> getEmployeesWithSpecificCompany(String companyID) {

        return employeeRepository.findAllByCompanyID(companyID);
    }

    public List<Company> getAllCompanyWithPage(int page, int pageSize) {
        return getAll().stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }


    public Company createCompany(Company updateCompany) {
        return companyRepository.save(updateCompany);
    }

    public Company updateCompany(String companyID, Company updateCompanyInfo) throws CompanyNotFoundException {
        this.getSpecificCompany(companyID);
        updateCompanyInfo.setCompanyID(companyID);
        return companyRepository.save(updateCompanyInfo);

    }

    public void deleteCompany(String companyID) throws CompanyNotFoundException {
        this.getSpecificCompany(companyID);
        companyRepository.deleteById(companyID);

    }
}
