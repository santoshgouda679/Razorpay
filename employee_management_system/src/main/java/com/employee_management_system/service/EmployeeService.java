package com.employee_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee_management_system.entity.Employee;
import com.employee_management_system.repository.EmployeeRepository;
@Service
public class EmployeeService {
	  private EmployeeRepository employeeRepository;

	    public EmployeeService(EmployeeRepository employeeRepository) {
	        this.employeeRepository = employeeRepository;
	    }

	    public String createEmployee(Employee employee) {
	        employeeRepository.save(employee);
	        return "employee data inserted";
	    }

	    public Employee fetchEmployeeById(String email) {
	        return employeeRepository.findById(email).get();
	    }

	    public List<Employee> fetchAllEmployee() {
	        return employeeRepository.findAll();
	    }

	    public String deleteEmployeeById(String email) {
	        employeeRepository.deleteById(email);
	        return "employee data deleted";
	    }

	    public String updateEmployeeById(String email, Employee employee) {

	        Employee existingEmployee =
	                employeeRepository.findById(email).get();

	        existingEmployee.setName(employee.getName());
	        existingEmployee.setSal(employee.getSal());
	        existingEmployee.setDepartment(employee.getDepartment());

	        employeeRepository.save(existingEmployee);

	        return "Employee Data Updated Successfully";
	    }
}
