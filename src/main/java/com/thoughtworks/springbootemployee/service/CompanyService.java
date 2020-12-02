package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    public List<Company> getAll() {
        return companyRepository.findAllCompany();
    }

    public Company getSpecificCompany(String companyID) {
        return companyRepository.findSpecificCompany(companyID);
    }

    public List<Employees> getEmployeesWithSpecificCompany(String companyID) {

        return null;
    }
}
