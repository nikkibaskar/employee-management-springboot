package com.nikhil.employeeDemo.employee_Management.repository;

import com.nikhil.employeeDemo.employee_Management.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}