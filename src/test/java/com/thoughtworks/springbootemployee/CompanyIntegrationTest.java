package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository1 companyRepository1;
    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
        companyRepository1.deleteAll();
    }
    @Test
    public void should_return_all_company_when_getAll_given_all_company() throws Exception{
        //given
        companyRepository1.save(new Company("companyA","1",20));


        //when
        // then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyID").value("1"))
                .andExpect(jsonPath("$[0].companyName").value("companyA"))
                .andExpect(jsonPath("$[0].employeeNum").value(20));



    }
    @Test
    void should_return_specific_company_when_getSpecificCompany_given_companyID_123() throws Exception {
        //given
        Company expected = new Company("companyA", 1);
        companyRepository1.save(expected);

        //when
        mockMvc.perform(get("/companies/" + expected.getCompanyID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyID").isString())
                .andExpect(jsonPath("$.companyName").value("companyA"))
                .andExpect(jsonPath("$.employeeNum").value(1));


        //then
    }

}
