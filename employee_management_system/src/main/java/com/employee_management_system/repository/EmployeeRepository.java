package com.employee_management_system.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_management_system.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, String>{
	

}
