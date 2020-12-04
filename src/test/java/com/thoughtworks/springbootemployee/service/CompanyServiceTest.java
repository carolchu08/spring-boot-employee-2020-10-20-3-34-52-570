package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;
    @InjectMocks
    private EmployeesService employeesService;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private EmployeeRepository employeeRepository;


    @Test
    void should_return_all_company_when_getAll_given_all_company() {
        //given
        List<Company> expected = Collections.singletonList(new Company("Name1", "123", 1));
        when(companyRepository.findAll()).thenReturn(expected);

        //when
        List<Company> actual = companyService.getAll();


        //then
        assertEquals(1, actual.size());

    }

    @Test
    void should_return_specific_company_when_getSpecificCompany_given_companyID_123() {
        //given
        Company expected = new Company("company a", "123", 1);
        when(companyRepository.findById("123")).thenReturn(java.util.Optional.of(expected));

        //when
        Company actual = companyService.getSpecificCompany("123");


        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_list_of_all_employee_when_getEmployeeWithSpecificCompany_given_companyID_1() {
        //given
        List<Employee> expectedEmployees = new ArrayList<>();
        Employee employee1 = new Employee("Name1", "121", 12, "male", 200, "1");
        Employee employee2 = new Employee("Name2", "111", 14, "femail", 2000, "1");
        expectedEmployees.add(employee1);
        expectedEmployees.add(employee2);
        when(employeeRepository.findAllByCompanyID("1")).thenReturn(expectedEmployees);
        //when
        List<Employee> actual = companyService.getEmployeesWithSpecificCompany("1");


        //then
        assertEquals(expectedEmployees, actual);
    }

    @Test
    void should_return_null_when_getEmployeeWithSpecificCompany_given_invalid_companyID() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Name1", "121", 12, "male", 200, "1"));
        employees.add(new Employee("Name2", "111", 14, "femail", 2000, "1"));
        when(employeeRepository.findAllByCompanyID("0")).thenReturn(null);
        //when
        List<Employee> actual = companyService.getEmployeesWithSpecificCompany("0");


        //then
        assertNull(actual);
    }

    @Test
    void should_return_2_companies_when_getAllCompanyWithPagination_given_employees_list_is_longer_than_2_and_pageNumber_is_1_and_pageSize_is_2() {
        //given
        Company company1 = new Company("Name1", "123", 4);
        Company company2 = new Company("Name2", "111", 1);
        Company company3 = new Company("Name3", "100", 1);
        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);

        when(companyRepository.findAll()).thenReturn(companies);
        //when
        List<Company> actual = companyService.getAllCompanyWithPage(1, 2);

        //then
        assertEquals(2, actual.size());
    }

    @Test
    void should_return_created_company_when_createCompany_given_no_company_in_database_and_a_new_company() {
        //given
        Company expectedCompany = new Company("Name1", "123", 1);
        when(companyRepository.save(expectedCompany)).thenReturn(expectedCompany);
        //when
        Company actual = companyService.createCompany(expectedCompany);

        //then
        assertEquals(expectedCompany, actual);

    }

    @Test
    void should_return_updated_company_when_updateCompany_given_companyID() throws CompanyNotFoundException {
        //given

        Company updateCompany = new Company("Name1", "123", 1);
        Company originalCompany = new Company("Name2", "123", 25);
        Company expectedCompany = new Company("Name1", "123", 1);
        when(companyRepository.findById("123")).thenReturn(Optional.of(originalCompany));
        //when
        companyService.updateCompany("123", updateCompany);
        final ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companyRepository, times(1)).save(companyArgumentCaptor.capture());

        //then

        final Company company = companyArgumentCaptor.getValue();
        assertEquals(expectedCompany.getCompanyID(), company.getCompanyID());
        assertEquals(expectedCompany.getEmployeeNum(), company.getEmployeeNum());

    }

    @Test
    void should_return_Company_Not_Found_when_updateCompany_given_invalid_companyID()  {
        //given
        Company updateCompany = new Company("Name1", "123", 1);
        when(companyRepository.findById("100")).thenReturn(Optional.empty());
        //when
        //Company actual = companyService.updateCompany("100", updateCompany);
        Exception exception = assertThrows(CompanyNotFoundException.class,()->companyService.updateCompany("100", updateCompany));

        //then
        assertEquals("Company Not Found", exception.getMessage());

    }

    @Test
    void should_return_removed_company_when_deleteCompany_given_invalid_companyID() throws CompanyNotFoundException {
        //when

        //then
        Exception exception = assertThrows(CompanyNotFoundException.class,()-> companyService.deleteCompany("123"));
        assertEquals("Company Not Found", exception.getMessage());
    }
    @Test
    void  should_throw_CompanyNotFoundException_and_have_a_company_removed_when_remove() throws CompanyNotFoundException {
        //when

        //then
        Exception exception = assertThrows(CompanyNotFoundException.class,()-> companyService.deleteCompany("123"));
        assertEquals("Company Not Found", exception.getMessage());
    }

}
