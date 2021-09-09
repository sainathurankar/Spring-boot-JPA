package com.jpa.crud.service;

import com.jpa.crud.entity.Employee;

import java.util.List;

public interface EmployeeServiceInterface {
    List<Employee> getEmployees();
    Employee addEmployee(Employee employee);

    Employee getById(Long id);

    void delete(long id);

    List<Employee> filter(String s);
}
