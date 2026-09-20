package com.employee_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Email
    @NotBlank(message = "Enter a valid email")
    private String email;
    @NotBlank(message = "must not be blank")
    private String name;
    @Positive(message = "sal should be more than 0")
    private double sal;
    @NotBlank(message = "must not be blank")
    private String department;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

	@Override
	public String toString() {
		return "Employee [email=" + email + ", name=" + name + ", sal=" + sal + ", department=" + department + "]";
	}
    
}