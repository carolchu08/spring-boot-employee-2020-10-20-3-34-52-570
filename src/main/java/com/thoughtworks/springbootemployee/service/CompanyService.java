package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
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

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getSpecificCompany(String companyID) {
        return companyRepository.findById(companyID).orElseThrow(RuntimeException::new);
    }

    public List<Employee> getEmployeesWithSpecificCompany(String companyID) {

            return employeeRepository.findAllByCompanyID(companyID);
    }

    public List<Company> getAllCompanyWithPage(int page, int pageSize) {
        return companyRepository.findAll().stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }


    public Company createCompany(Company updateCompany) {
        return companyRepository.save(updateCompany);
    }

    public Company updateCompany(String companyID, Company updateCompanyInfo) throws ResponseStatusException {
        companyRepository.findById(companyID).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Company Not Found"));
            updateCompanyInfo.setCompanyID(companyID);
            return companyRepository.save(updateCompanyInfo);

    }

    public void deleteCompany(String companyID) throws ResponseStatusException {
        Company originalCompany = companyRepository.findById(companyID).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Company Not Found"));
        if (originalCompany!=null) {
            companyRepository.deleteById(companyID);
        }
    }
}
