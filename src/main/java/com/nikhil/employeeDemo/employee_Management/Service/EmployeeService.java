package com.nikhil.employeeDemo.employee_Management.Service;

import com.nikhil.employeeDemo.employee_Management.Model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id); // <-- FIXED!
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee updated);
    void deleteEmployee(Long id);
}
