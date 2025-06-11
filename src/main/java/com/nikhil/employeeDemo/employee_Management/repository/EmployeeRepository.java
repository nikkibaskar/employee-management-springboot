package com.nikhil.employeeDemo.employee_Management.repository;

import com.nikhil.employeeDemo.employee_Management.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}