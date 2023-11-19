package com.luv2code.springboot.demosecurity.service;

import com.luv2code.springboot.demosecurity.dao.EmployeeRepository;
import com.luv2code.springboot.demosecurity.entity.Employee;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee employee = null;
        if (result.isPresent()){
            employee = result.get();
        }else {
            throw  new RuntimeException("Did not find employee id - " + theId);
        }
        return employee;
    }
    @Transactional
    @Override
    public void delete(int id) {
        employeeRepository.delete(findById(id));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
