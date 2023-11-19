package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.exception.EmployeeNotFoundException;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployees(@PathVariable int employeeId){
        Employee theEmployee =  employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //just in case they pass an id in the JSON.. set id to 0
        //this is to force save of new item.. instead of update
        theEmployee.setId(0);

        return employeeService.save(theEmployee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        return employeeService.save(theEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }

        employeeService.delete(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
