package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void should_return_all_employees_when_get_ALl_given_all_employees() throws Exception {
        //given
        Employee employee = new Employee("Peter", 12, "male", 1000, "1");
        employeeRepository.save(employee);
        //when

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeID").isString())
                .andExpect(jsonPath("$[0].employeeName").value("Peter"))
                .andExpect(jsonPath("$[0].age").value(12))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(1000));
        //then

    }

    @Test
    public void should_return_employee_when_create_employee_given_employee() throws Exception {
        //given
        String employeeAsJson = "{\n" +
                "    \"employeeName\": \"Alan\",\n" +
                "    \"age\":35,\n" +
                "    \"gender\":\"male\",\n" +
                "    \"salary\":2000,\n" +
                "    \"companyID\":\"1\"\n" +
                "\n" +
                "}";
        //when
        //then
        mockMvc.perform(post("/employees")
                .contentType(APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeID").isString())
                .andExpect(jsonPath("$.employeeName").value("Alan"))
                .andExpect(jsonPath("$.age").value(35))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(2000))
                .andExpect(jsonPath("$.companyID").value("1"));

        List<Employee> employeeList = employeeRepository.findAll();
        assertEquals(1, employeeList.size());


    }

    @Test
    public void should_return_the_employee_when_getOneEmployee_given_a_valid_employee_id() throws Exception {
        //given
        employeeRepository.save(new Employee("Alan", 12, "male", 1000, "1"));
        employeeRepository.save(new Employee("Leo", 12, "male", 1000, "2"));
        employeeRepository.save(new Employee("David", 12, "male", 1000, "3"));
        employeeRepository.save(new Employee("Ken", 12, "male", 1000, "4"));
        Employee expectedEmployee = new Employee("Linda", 20, "female", 10000, "6");
        employeeRepository.save(expectedEmployee);
        //when
        //then
        mockMvc.perform(get("/employees/" + expectedEmployee.getEmployeeID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeID").isString())
                .andExpect(jsonPath("$.employeeName").value("Linda"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(10000))
                .andExpect(jsonPath("$.companyID").value("6"));

    }

    @Test
    public void should_return_exception_when_getOneEmployee_given_a_invalid_employee_id() throws Exception {
        //given
        Employee employee = new Employee("Sam", 18, "Male", 20000, "1");
        Employee addedEmployee = this.employeeRepository.save(employee);
        this.employeeRepository.deleteAll();

        //when
        //then
        this.mockMvc.perform(get("/employees/" + addedEmployee.getEmployeeID()))
                .andExpect(status().isNotFound());

    }

    @Test
    void should_return_2_employees_when_getAllEmployeesWithPagination_given_employees_list_is_longer_than_2_and_pageNumber_is_1_and_pageSize_is_2() throws Exception {
        //given
        employeeRepository.save(new Employee("Name1", 13, "male", 100, "1"));
        employeeRepository.save(new Employee("Name2", 13, "male", 100, "1"));
        employeeRepository.save(new Employee("Name3", 13, "male", 100, "1"));
        employeeRepository.save(new Employee("Name4", 13, "male", 100, "1"));

        //when
        mockMvc.perform(get("/employees?page=1&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeID").isString())
                .andExpect(jsonPath("$[0].employeeName").value("Name1"))
                .andExpect(jsonPath("$[1].employeeName").value("Name2"));
    }

    @Test
    void should_return_male_employee_when_getEmployeeWithMatchedGender_given_gender_of_employee_is_male() throws Exception {
        //given
        //when
        employeeRepository.save(new Employee("Name1", 13, "male", 100, "1"));
        employeeRepository.save(new Employee("Name2", 13, "female", 100, "1"));
        //then
        mockMvc.perform(get("/employees?gender=male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeID").isString())
                .andExpect(jsonPath("$[0].employeeName").value("Name1"))
                .andExpect(jsonPath("$[0].gender").value("male"));

    }

    @Test
    void should_return_updated_employee_when_updateEmployee_given_employeeID() throws Exception {
        employeeRepository.save(new Employee("Peter", 19, "male", 999, "1"));
        employeeRepository.save(new Employee("Ken", 29, "male", 999, "2"));
        Employee employee = new Employee("Alan", 15, "male", 999, "3");
        Employee expected = employeeRepository.save(employee);

        String updateAsJson = "{\n" +
                "    \"employeeName\": \"Alan\",\n" +
                "    \"age\":35,\n" +
                "    \"gender\":\"male\",\n" +
                "    \"salary\":2000,\n" +
                "    \"companyID\":\"1\"\n" +
                "\n" +
                "}";
        Employee update = new Employee("Alan", 35, "male", 2000, "1");

        //when
        //then
        mockMvc
                .perform(
                        put("/employees/" + expected.getEmployeeID())
                                .contentType(APPLICATION_JSON)
                                .content(updateAsJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.employeeID").value(expected.getEmployeeID()))
                .andExpect(jsonPath("$.employeeName").value(update.getEmployeeName()))
                .andExpect(jsonPath("$.age").value(update.getAge()))
                .andExpect(jsonPath("$.gender").value(update.getGender()))
                .andExpect(jsonPath("$.salary").value(update.getSalary()))
                .andExpect(jsonPath("$.companyID").value(update.getCompanyID()));
    }

    @Test
    void should_return_removed_employee_when_deleteEmployee_given_valid_employeeID() throws Exception {
        //given
        Employee employee = new Employee("Alan", 15, "male", 999, "3");
        Employee expected = employeeRepository.save(employee);
        //when
        mockMvc.perform(delete("/employees/" + expected.getEmployeeID()))
                .andExpect(status().isOk());
        //then

    }

}
