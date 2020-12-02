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
    @Test
    void should_return_list_of_all_employee_when_getEmployeeWithSpecificCompany_given_invalid_companyID(){
        //given
        List <Employees> employees = new ArrayList<>();
        employees.add(new Employees("Name1","121",12,"male",200));
        employees.add(new Employees("Name2","111",14,"femail",2000));
        Company company = new Company("Name1","123",1,employees);
        when(companyRepository.findSpecificCompany("111")).thenReturn(null);
        //when
        List <Employees> actual = companyService.getEmployeesWithSpecificCompany("111");


        //then
        assertNull(actual);
    }

    @Test
    void should_return_3_companies_when_getAllCompanyWithPagination_given_employees_list_is_longer_than_3_and_pageNumber_is_1_and_pageSize_is_3() {
        //given
        Employees employees1 = new Employees("Name1","1",13,"male",100);
        Employees employees2 = new Employees("Name2","2",13,"male",100);
        Employees employees3 = new Employees("Name3","3",13,"male",100);
        Employees employees4 = new Employees("Name4","4",13,"male",100);
        Employees employees5 = new Employees("Name5","5",15,"male",100);
        Employees employees6 = new Employees("Name6","6",19,"male",100);
        List<Employees> employee1 = new ArrayList<>();
        List<Employees> employee2 = new ArrayList<>();
        List<Employees> employee3 = new ArrayList<>();
        employee1.add(employees1);
        employee1.add(employees2);
        employee1.add(employees3);
        employee1.add(employees4);
        employee2.add(employees5);
        employee3.add(employees6);
        Company company1 = new Company("Name1","123",4,employee1);
        Company company2 = new Company("Name2","111",1,employee2);
        Company company3 = new Company("Name3","100",1,employee3);
        List<Company> companies= new ArrayList<>();
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);

        when(companyRepository.findAllCompany()).thenReturn(companies);
        //when
        List<Company> actual = companyService.getAllCompanyWithPage(1,3);

        //then
        assertEquals(3,actual.size());
    }

    @Test
    void should_return_created_company_when_createCompany_given_no_company_in_database_and_a_new_company() {
        //given
        List <Employees> employees = new ArrayList<>();
        employees.add(new Employees("Name1","121",12,"male",200));
        employees.add(new Employees("Name2","111",14,"femail",2000));
        Company expectedCompany = new Company("Name1","123",1,employees);
        when(companyRepository.createCompany(expectedCompany)).thenReturn(expectedCompany);
        //when
        Company actual= companyService.createCompany(expectedCompany);

        //then
        assertEquals(expectedCompany,actual);

    }
    @Test
    void should_return_updated_company_when_updateCompany_given_companyID() {
        //given
        Employees employees1 = new Employees("Name1","1",13,"male",100);
        Employees employees2 = new Employees("Name2","2",13,"male",100);
        List<Employees> employeeList = new ArrayList<>();
        employeeList.add(employees1);
       employeeList.add(employees2);
        Company updateCompany = new Company("Name1","123",1,employeeList);
        when(companyRepository.updateCompany("123",updateCompany)).thenReturn(updateCompany);
        //when
        Company actual= companyService.updateCompany("123",updateCompany);

        //then
        assertEquals(updateCompany,actual);

    }
    @Test
    void should_return_updated_company_when_updateCompany_given_invalid_companyID() {
        //given
        Employees employees1 = new Employees("Name1","1",13,"male",100);
        Employees employees2 = new Employees("Name2","2",13,"male",100);
        List<Employees> employeeList = new ArrayList<>();
        employeeList.add(employees1);
        employeeList.add(employees2);
        Company updateCompany = new Company("Name1","123",1,employeeList);
        when(companyRepository.updateCompany("100",updateCompany)).thenReturn(null);
        //when
        Company actual= companyService.updateCompany("100",updateCompany);

        //then
        assertNull(actual);

    }
    @Test
    void should_return_removed_company_when_deleteCompany_given_valid_companyID() {
        //given
        Employees employees1 = new Employees("Name1","1",13,"male",100);
        Employees employees2 = new Employees("Name2","2",13,"male",100);
        List<Employees> employeeList = new ArrayList<>();
        employeeList.add(employees1);
        employeeList.add(employees2);
        Company deleteCompany = new Company("Name1","123",1,employeeList);
        when(companyRepository.deleteCompany("123",deleteCompany)).thenReturn(deleteCompany);
        //when
        Company actual = companyService.deleteCompany("123",deleteCompany);
        //then
        assertEquals(deleteCompany,actual);
    }
    @Test
    void should_return_removed_company_when_deleteCompany_given_invalid_companyID() {
        //given
        Employees employees1 = new Employees("Name1","1",13,"male",100);
        Employees employees2 = new Employees("Name2","2",13,"male",100);
        List<Employees> employeeList = new ArrayList<>();
        employeeList.add(employees1);
        employeeList.add(employees2);
        Company deleteCompany = new Company("Name1","123",1,employeeList);
        when(companyRepository.deleteCompany("111",deleteCompany)).thenReturn(null);
        //when
        Company actual = companyService.deleteCompany("111",deleteCompany);
        //then
        assertNull(actual);
    }

}
