package com.luv2code.springboot.demosecurity.service;

import com.luv2code.springboot.demosecurity.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);
    Employee findById(int theId);
    void delete(int id);
    List<Employee> findAll();

}
