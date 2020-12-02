package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeesRepository {
    private final List<Employees> employees = new ArrayList<>();

    public List<Employees> findAllEmployees (){
        return this.employees;
    }
    public Employees findOneEmployee(String employeeID) {
        return employees.stream().filter(employees -> employees.getEmployeeID().equals(employeeID)).findFirst().orElse(null);
    }
    public List <Employees> getPagination (int page, int pageSize){
        return getPage(employees, page, pageSize);
    }
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() <= fromIndex) {
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }
}
