package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
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

    public List<Employee> getEmployeesWithSpecificCompany(String companyID) {
        Company specificCompany = this.getSpecificCompany(companyID);
        if (specificCompany != null) {
            return specificCompany.getEmployees();
        }
        return null;
    }

    public List<Company> getAllCompanyWithPage(int page, int pageSize) {
        return companyRepository.findAllCompany().stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }


    public Company createCompany(Company updateComapny) {
        return companyRepository.createCompany(updateComapny);
    }

    public Company updateCompany(String companyID, Company updateCompany) {
        return companyRepository.updateCompany(companyID, updateCompany);
    }

    public Company deleteCompany(String companyID, Company deleteCompany) {
        return companyRepository.deleteCompany(companyID, deleteCompany);
    }
}
