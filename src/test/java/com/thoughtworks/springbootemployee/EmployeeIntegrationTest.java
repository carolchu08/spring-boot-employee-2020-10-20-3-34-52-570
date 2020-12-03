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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EmployeeRepository employeeRepository;
    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
    }
    @Test
    public void should_return_all_employees_when_get_ALl_given_all_employees() throws Exception {
        //given
        Employee employee = new Employee("Peter",12,"male",1000,"1");
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
        String employeeAsJson =" {\n" +
                "        \"employeeID\": \"5fc8bd2a50fa7733ae675adb\",\n" +
                "        \"employeeName\": \"peter\",\n" +
                "        \"age\": 13,\n" +
                "        \"salary\": 10000,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"companyID\": \"1\"\n" +
                "    }";
        //when
        //then
        mockMvc.perform(post("/employees")
                .contentType(APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeID").isString())
                .andExpect(jsonPath("$.employeeName").value("peter"))
                .andExpect(jsonPath("$.age").value(13))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(10000))
                .andExpect(jsonPath("$.companyID").value("1"));

        List<Employee> employeeList = employeeRepository.findAll();
        assertEquals(1,employeeList.size());


    }
    @Test
    public void should_return_the_employee_when_getOneEmployee_given_a_valid_employee_id() throws Exception {
        //given
        employeeRepository.save(new Employee("Alan",12,"male",1000,"1"));
        employeeRepository.save(new Employee("Leo",12,"male",1000,"2"));
        employeeRepository.save(new Employee("David",12,"male",1000,"3"));
        employeeRepository.save(new Employee("Ken",12,"male",1000,"4"));
        Employee expectedEmployee = new Employee("Linda",20,"female",10000,"6");
        employeeRepository.save(expectedEmployee);
        //when
        //then
        mockMvc.perform(get("/employees/"+expectedEmployee.getEmployeeID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeID").isString())
                .andExpect(jsonPath("$.employeeName").value("Linda"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(10000))
                .andExpect(jsonPath("$.companyID").value("6"));

    }
    
}
