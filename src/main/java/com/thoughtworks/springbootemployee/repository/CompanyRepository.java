package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;;
import java.util.List;
@Repository
public class CompanyRepository {
    private final List<Company> company = new ArrayList<>();

    public List<Company> findAllCompany() {
        return this.company;
    }

    public Company findSpecificCompany(String companyID) {
        return company.stream().filter(company1 -> company1.getCompanyID().equals(companyID))
                .findFirst()
                .orElse(null);
    }

    public Company createCompany(Company updateCompany) {
        company.add(updateCompany);
        return updateCompany;
    }

    public Company updateCompany(String companyID, Company updatecompany) {
        company.stream().filter(company1 -> company1.getCompanyID().equals(companyID))
                .findFirst()
                .ifPresent(company1 -> {
                    company.remove(company1);
                    company.add(updatecompany);
                });
        return updatecompany;
    }

    public Company deleteCompany(String companyID, Company deleteCompany) {
        company.stream().filter(company1 -> company1.getCompanyID().equals(companyID))
                .findFirst()
                .ifPresent(company::remove);
        return deleteCompany;
    }
}
