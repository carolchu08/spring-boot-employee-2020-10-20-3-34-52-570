package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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




}
