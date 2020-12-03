package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository1 companyRepository;
    private EmployeeRepository employeeRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getSpecificCompany(String companyID) {
        return companyRepository.findById(companyID).orElse(null);
    }

    public List<Employee> getEmployeesWithSpecificCompany(String companyID) {

            return employeeRepository.findAllByCompanyID(companyID);
    }

    public List<Company> getAllCompanyWithPage(int page, int pageSize) {
        return companyRepository.findAll().stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }


    public Company createCompany(Company updateComapny) {
        return companyRepository.save(updateComapny);
    }

    public Company updateCompany(String companyID, Company updateCompany) {
        Company originalCompany = companyRepository.findById(companyID).orElse(null);
        if(originalCompany!=null ) {
            updateCompany.setCompanyID(companyID);
            return companyRepository.save(updateCompany);
        }
        return null;
    }

    public void deleteCompany(String companyID) {
        companyRepository.deleteById(companyID);
    }
}
