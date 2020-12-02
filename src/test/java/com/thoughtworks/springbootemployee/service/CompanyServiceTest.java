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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
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
        List<Company> expected = Collections.singletonList(new Company("Name1","123",1,employees));
        when(companyRepository.findAllCompany()).thenReturn(expected);

        //when
        List<Company> actual = companyService.getAll();


        //then
        assertEquals(1,actual.size());

    }
    @Test
    void should_return_specific_company_when_getSpecificCompany_given_companyID_123(){
        //given
        List <Employees> employees = new ArrayList<>();
        employees.add(new Employees("Name1","123",12,"male",200));
        Company expected = new Company("Name1","123",1,employees);
        when(companyRepository.findSpecificCompany("123")).thenReturn(expected);

        //when
        Company actual = companyService.getSpecificCompany("123");


        //then
        assertEquals(expected,actual);
    }
    @Test
    void should_return_list_of_all_employee_when_getEmployeeWithSpecificCompany_given_companyID_123(){
        //given
        List <Employees> employees = new ArrayList<>();
        employees.add(new Employees("Name1","121",12,"male",200));
        employees.add(new Employees("Name2","111",14,"femail",2000));
        Company expected = new Company("Name1","123",1,employees);
        when(companyRepository.findSpecificCompany(anyString())).thenReturn(expected);
        //when
        List <Employees> actual = companyService.getEmployeesWithSpecificCompany("123");


        //then
        assertEquals(employees,actual);
    }


}
