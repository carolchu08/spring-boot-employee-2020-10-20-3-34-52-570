package com.thoughtworks.springbootemployee.mapping;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import sun.misc.Contended;

@Component
public class EmployeeMapper {
public Employee toEntity(EmployeeRequest employeeRequest){
    Employee employee = new Employee();

    BeanUtils.copyProperties(employeeRequest,employee);
    return employee;
}
public EmployeeResponse toResponse(Employee employee){
    EmployeeResponse employeeResponse = new EmployeeResponse();
    BeanUtils.copyProperties(employee,employeeResponse);
    return employeeResponse;
}
}
