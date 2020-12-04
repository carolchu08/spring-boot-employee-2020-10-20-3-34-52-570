package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    CompanyRepository companyRepository;
    @AfterEach
    void tearDown() {

        companyRepository.deleteAll();
        employeeRepository.deleteAll();
    }
    @Test
    public void should_return_all_company_when_getAll_given_all_company() throws Exception{
        //given
        companyRepository.save(new Company("companyA",20));


        //when
        // then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyID").isString())
                .andExpect(jsonPath("$[0].companyName").value("companyA"))
                .andExpect(jsonPath("$[0].employeeNum").value(20));



    }
    @Test
    void should_return_specific_company_when_getSpecificCompany_given_companyID_123() throws Exception {
        //given
        Company expected = new Company("companyA", 1);
        companyRepository.save(expected);

        //when
        mockMvc.perform(get("/companies/" + expected.getCompanyID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyID").isString())
                .andExpect(jsonPath("$.companyName").value("companyA"))
                .andExpect(jsonPath("$.employeeNum").value(1));


        //then
    }
    @Test
    void should_return_2_companies_when_getAllCompanyWithPagination_given_employees_list_is_longer_than_2_and_pageNumber_is_1_and_pageSize_is_2() throws Exception {
        //given
        Company company1 = new Company("Name1",  4);
        Company company2 = new Company("Name2",  1);
        Company company3 = new Company("Name3",  1);
        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);


        //when
        //then
        mockMvc.perform(get("/companies?page=1&pageSize=2"))
                .andExpect(jsonPath("$[0].companyID").isString())
                .andExpect(jsonPath("$[0].companyName").value("Name1"))
                .andExpect(jsonPath("$[0].employeeNum").value(4))
                .andExpect(jsonPath("$[1].companyID").isString())
                .andExpect(jsonPath("$[1].companyName").value("Name2"))
                .andExpect(jsonPath("$[1].employeeNum").value(1));
    }
    @Test
    void should_return_removed_employee_when_deleteEmployee_given_valid_companyID() throws Exception {
        //given
        Company company1 = new Company("Name1",  4);
        companyRepository.save(company1);
        //when
        mockMvc.perform(delete("/companies/"+company1.getCompanyID()))
                .andExpect(status().isOk());
        //then

    }
    @Test
    void should_return_created_company_when_createCompany_given_no_company_in_database_and_a_new_company() throws Exception {
        //given
        //when
        //then
            String createAsJson ="{\n" +
                    "    \"companyName\": \"companyA\",\n" +
                    "    \"employeeNum\":\"1\"\n" +
                    "}";
            mockMvc.perform(post("/companies")
                    .contentType(APPLICATION_JSON)
                    .content(createAsJson))
                    .andExpect(jsonPath("$.companyID").isString())
                    .andExpect(jsonPath("$.companyName").value("companyA"))
                    .andExpect(jsonPath("$.employeeNum").value(1));


    }
    @Test
    void should_return_updated_company_when_updateCompany_given_companyID() throws Exception {
        //given

        Company company1 = new Company("companyA", 1);
        Company company2 = new Company("companyB",  25);
        Company company3 = new Company("companyC",  1);
        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);
        Company expected = new Company("companyE",30);
        String updateAsJson ="{\n" +
                "    \"companyName\": \"companyE\",\n" +
                "    \"employeeNum\":\"30\"\n" +
                "}";
        mockMvc.perform(put("/companies/"+company1.getCompanyID())
                .contentType(APPLICATION_JSON)
                .content(updateAsJson))
                .andExpect(jsonPath("$.companyID").value(company1.getCompanyID()))
                .andExpect(jsonPath("$.companyName").value(expected.getCompanyName()))
                .andExpect(jsonPath("$.employeeNum").value(expected.getEmployeeNum()));

    }
    @Test
    void should_return_not_found_when_updateCompany_given_invalid_companyID() throws Exception {
        //given
        String updateAsJson ="{\n" +
                "    \"companyName\": \"companyE\",\n" +
                "    \"employeeNum\":\"30\"\n" +
                "}";

        Company company1 = new Company("companyA", 1);
        companyRepository.save(company1);
        companyRepository.deleteAll();
        mockMvc.perform(put("/companies/"+company1.getCompanyID()).contentType(APPLICATION_JSON).content(updateAsJson))
                .andExpect(status().isNotFound());

    }
    @Test
    void should_return_empty_list_when_getAll_given_after_deleted_company() throws Exception {
        //given
        //when
        //then
        Company company3 = new Company("companyC",  1);
        companyRepository.save(company3);

        mockMvc
                .perform(
                        delete("/companies/"+company3.getCompanyID())
                )
                .andExpect(status().isOk());
        mockMvc
                .perform(
                        get("/companies")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

  @Test
  public void should_return_employee_list_when_get_company_employee_list_given_company_and_employees() throws Exception {
      //given
      Company company = new Company();
      company.setCompanyID("1");
      Employee employee1 = new Employee("employee1", 12, "Male", 100, company.getCompanyID());
      Employee employee2 = new Employee("employee2", 2, "Female", 150, company.getCompanyID());
      companyRepository.insert(company);
      employeeRepository.insert(employee1);
      employeeRepository.insert(employee2);
      companyRepository.save(company);

      //when
      mockMvc.perform(get("/companies/" + company.getCompanyID() + "/employees"))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$", hasSize(2)))
              .andExpect(jsonPath("$[0].employeeName").value("employee1"))
              .andExpect(jsonPath("$[1].employeeName").value("employee2"));
  }
}
