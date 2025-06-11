package com.nikhil.employeeDemo.employee_Management.Service;

import com.nikhil.employeeDemo.employee_Management.Model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    Department createDepartment(Department department);
    Department updateDepartment(Long id, Department department);
    void deleteDepartment(Long id);
}