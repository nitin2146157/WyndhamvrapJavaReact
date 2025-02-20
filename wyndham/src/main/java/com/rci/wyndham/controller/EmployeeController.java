package com.rci.wyndham.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rci.wyndham.entity.Employee;
import com.rci.wyndham.repository.EmployeeRepository;

import java.util.List;
 
@RestController
@RequestMapping("/employees")
public class EmployeeController {
 
    @Autowired
    private EmployeeRepository employeeRepository;
 
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
 
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
 
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
 
        employee.setName(updatedEmployee.getName());
        employee.setSalary(updatedEmployee.getSalary());
        return employeeRepository.save(employee);
    }
 
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeRepository.deleteById(id);
        return "Employee with ID " + id + " deleted";
    }
}