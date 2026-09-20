package com.employee_management_system.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_management_system.entity.Employee;
import com.employee_management_system.repository.EmployeeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Create Employee
    @PostMapping
    public String createEmployee(@Valid @RequestBody Employee employee) {
        employeeRepository.save(employee);
        return "Employee data inserted";
//    	System.out.println(employee);
    }

    // Fetch Employee by email (since email is @Id)
    @GetMapping("/{email}")
    public Employee fetchEmployeeById(@PathVariable String email) {
        return employeeRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Fetch all Employees
    @GetMapping
    public List<Employee> fetchAllEmployee() {
        return employeeRepository.findAll();
    }

    // Delete Employee by email
    @DeleteMapping("/{email}")
    public String deleteEmployeeById(@PathVariable String email) {
        employeeRepository.deleteById(email);
        return "Employee data deleted";
    }

    // Update Employee by email
    @PutMapping("/{email}")
    public String updateEmployeeById(@PathVariable String email,
                                     @RequestBody Employee employee) {
        Employee existingEmployee = employeeRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setSal(employee.getSal());
        existingEmployee.setDepartment(employee.getDepartment());

        employeeRepository.save(existingEmployee);

        return "Employee Data Updated Successfully";
    }
}
