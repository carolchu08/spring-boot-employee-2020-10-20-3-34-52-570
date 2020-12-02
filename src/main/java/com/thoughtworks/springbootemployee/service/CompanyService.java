package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
        Company specificCompany = this.getSpecificCompany(companyID);
        if (specificCompany != null) {
            return specificCompany.getEmployees();
        }
        return null;
    }

    public List<Company> getAllCompanyWithPage(int page, int pageSize) {
        return getPage(companyRepository.findAllCompany(), page, pageSize);
    }

    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() <= fromIndex) {
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
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
