package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;
    @Mock
    private CompanyRepository companyRepository;
    @Test
    void should_return_all_company_when_getAll_given_all_company() {
        //given
        List <Employees> employees = new ArrayList<>();
        employees.add(new Employees("Name1","123",12,"male",200));
        List<Company> expected = Collections.singletonList(new Company("Name1",1,employees));
        when(companyRepository.findAllCompany()).thenReturn(expected);

        //when
        List<Company> actual = companyService.getAll();


        //then
        assertEquals(1,actual.size());

    }
}
