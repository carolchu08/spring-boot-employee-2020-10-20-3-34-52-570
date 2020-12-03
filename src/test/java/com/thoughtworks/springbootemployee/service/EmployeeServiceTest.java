package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
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
    private EmployeeRepository employeeRepository;
    @Test
    void should_return_all_employees_when_getAll_given_all_employees() {
        //given
        List <Employee> expected = Arrays.asList(new Employee("Peter","123",13,"male",10000));
        when(employeeRepository.findAllEmployees()).thenReturn(expected);

        //when
        List<Employee> actual = employeeService.getAllEmployees();


        //then
        assertEquals(1,actual.size());

    }
    @Test
    void should_return_the_employee_when_getOne_given_a_valid_employee_id() {
        //given
        Employee expectedEmployee = new Employee("Ken","123",15,"male",1200);
        when(employeeRepository.findOneEmployee(any())).thenReturn(expectedEmployee);


        //when
        Employee actual = employeeService.getOneEmployee("123");

        //then
        assertEquals(expectedEmployee,actual);

    }
    @Test
    void should_return_null_when_getOne_given_a_valid_employee_id() {
        //given
        Employee expectedEmployee = new Employee("Ken","123",15,"male",1200);
        when(employeeRepository.findOneEmployee("111")).thenReturn(null);


        //when
        Employee actual = employeeService.getOneEmployee("111");

        //then
        assertNull(actual);

    }
    @Test
    void should_return_3_employees_when_getAllEmployeesWithPagination_given_employees_list_is_longer_than_3_and_pageNumber_is_1_and_pageSize_is_3() {
        //given
        Employee employee1 = new Employee("Name1","1",13,"male",100);
        Employee employee2 = new Employee("Name2","2",13,"male",100);
        Employee employee3 = new Employee("Name3","3",13,"male",100);
        Employee employee4 = new Employee("Name4","4",13,"male",100);
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(employee1);
        expectedEmployees.add(employee2);
        expectedEmployees.add(employee3);
        expectedEmployees.add(employee4);

        //when
        when(employeeRepository.findAllEmployees()).thenReturn(expectedEmployees);
        List<Employee> actual = employeeService.getPagination(1,3);

        //then
        assertEquals(3,actual.size());
    }
    @Test
    void should_return_created_employee_when_createEmployee_given_no_employee_in_database_and_a_new_employee() {
        //given
        Employee expectedEmployee = new Employee("Peter","123",13,"male",2000);
        when(employeeRepository.createEmployee(expectedEmployee)).thenReturn(expectedEmployee);
        //when
        Employee actual= employeeService.createEmployee(expectedEmployee);

        //then
        assertEquals(expectedEmployee,actual);

    }
    @Test
    void should_return_male_employee_when_getEmployeeWithMatchedGender_given_gender_of_employee_is_male() {
        //given
        Employee employee1 = new Employee("Peter","123",13,"male",2000);
        Employee employee2 = new Employee("May","100",13,"female",2000);
        List<Employee> expectedEmployee = new ArrayList<>();
        expectedEmployee.add(employee2);
        expectedEmployee.add(employee1);
        when(employeeRepository.findAllEmployees()).thenReturn(expectedEmployee);
        //when
        List <Employee> actual= employeeService.getEmployeeWithSameGender("male");

        //then
        assertEquals(1,actual.size());

    }
    @Test
    void should_return_updated_employee_when_updateEmployee_given_employeeID() {
        //given
        Employee updateEmployee = new Employee("Jenny","123",13,"female",2000);
        when(employeeRepository.updateEmployee("123",updateEmployee)).thenReturn(updateEmployee);
        //when
        Employee actual= employeeService.updateEmployee(updateEmployee.getEmployeeID(),updateEmployee);

        //then
        assertEquals(updateEmployee,actual);
    }
    @Test
    void should_return_removed_employee_when_deleteEmployee_given_valid_employeeID() {
        //given
        Employee deleteEmployee = new Employee("Peter","123",13,"male",2000);
        when(employeeRepository.deleteEmployee("123",deleteEmployee)).thenReturn(deleteEmployee);
        //when
        Employee actual = employeeService.deleteEmployee("123",deleteEmployee);
        //then
        assertEquals(deleteEmployee,actual);
    }

    @Test
    void should_return_null_when_deleteEmployee_given_invalid_employeeID() {
        //given
        Employee deleteEmployee = new Employee("Peter","123",13,"male",2000);
        when(employeeRepository.deleteEmployee("111",deleteEmployee)).thenReturn(null);
        //when
        Employee actual = employeeService.deleteEmployee("111",deleteEmployee);
        //then
        assertNull(actual);
    }

}
