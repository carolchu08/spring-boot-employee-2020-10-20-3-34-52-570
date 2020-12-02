package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;

import java.util.ArrayList;
import java.util.List;

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
}
