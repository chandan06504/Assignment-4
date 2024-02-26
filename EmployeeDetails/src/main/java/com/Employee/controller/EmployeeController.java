package com.Employee.controller;

import com.Employee.entity.Employee;
import com.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping("")
    public String homepage(){
        return "index";
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/save")
    public String saveEmployee(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String location
    ) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setLocation(location);

        employeeRepository.save(employee);

        return "Employee saved successfully.";
    }

    @GetMapping("/displayAll")
    public List<Employee> displayAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/display/{id}")
    public ResponseEntity<Employee> displayEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).get();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}

