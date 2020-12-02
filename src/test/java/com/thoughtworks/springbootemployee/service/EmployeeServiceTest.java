package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeesService employeeService;
    @Mock
    private EmployeesRepository employeeRepository;
    @Test
    void should_return_all_employees_when_getAll_given_all_employees() {
        //given
        List <Employees> expected = Arrays.asList(new Employees("Peter","123",13,"male",10000));
        when(employeeRepository.findAllEmployees()).thenReturn(expected);

        //when
        List<Employees> actual = employeeService.getAll();


        //then
        assertEquals(1,actual.size());

    }
    @Test
    void should_return_the_employee_when_getOne_given_a_valid_employee_id() {
        //given
        Employees expectedEmployees = new Employees("Ken","123",15,"male",1200);
        when(employeeRepository.findOneEmployee(any())).thenReturn(expectedEmployees);


        //when
        Employees actual = employeeService.getOneEmployee("123");

        //then
        assertEquals(expectedEmployees,actual);

    }
    @Test
    void should_return_null_when_getOne_given_a_valid_employee_id() {
        //given
        Employees expectedEmployees = new Employees("Ken","123",15,"male",1200);
        when(employeeRepository.findOneEmployee("111")).thenReturn(null);


        //when
        Employees actual = employeeService.getOneEmployee("111");

        //then
        assertNull(actual);

    }
    @Test
    void should_return_3_employees_when_getAllEmployeesWithPagination_given_employees_list_is_longer_than_3_and_pageNumber_is_1_and_pageSize_is_3() {
        //given
        Employees employees1 = new Employees("Name1","1",13,"male",100);
        Employees employees2 = new Employees("Name2","2",13,"male",100);
        Employees employees3 = new Employees("Name3","3",13,"male",100);
        Employees employees4 = new Employees("Name4","4",13,"male",100);
        List<Employees> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(employees1);
        expectedEmployees.add(employees2);
        expectedEmployees.add(employees3);


        //when
        when(employeeRepository.getPagination(1,3)).thenReturn(expectedEmployees);
        List<Employees> actual = employeeService.getPagination(1,3);

        //then
        assertEquals(expectedEmployees,actual);
        assertEquals(3,actual.size());
    }





}
