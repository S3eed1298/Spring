package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.exception.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    //@Transactional no need
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public Employee findById(Integer id) {
        Optional<Employee> result = employeeRepository.findById(id);
        //optional type is different pattern instead of having to check for nulls, new feature in java 8

        Employee theEmployee = null;
        if(result.isPresent()){
            theEmployee = result.get();
        }else{
            throw new EmployeeNotFoundException("Did not find employee id - " + id);
        }

        return theEmployee;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    //@Transactional no need
    public void delete(Integer id) {
        Employee theEmployee = findById(id);
        employeeRepository.delete(theEmployee);
    }
}
