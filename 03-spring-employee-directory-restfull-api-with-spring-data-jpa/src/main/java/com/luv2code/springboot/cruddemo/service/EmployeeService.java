package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee theEmployee);

    Employee findById(Integer id);

    List<Employee> findAll();

    void delete(Integer id);
}
