package com.nikhil.employeeDemo.employee_Management.Service;

import jakarta.persistence.EntityNotFoundException;
import com.nikhil.employeeDemo.employee_Management.Model.Employee;
import com.nikhil.employeeDemo.employee_Management.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        logger.debug("Retrieving all employees from database");
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        logger.debug("Retrieving employee with ID: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + id + " not found"));
    }


    @Override
    public Employee createEmployee(Employee employee) {
        logger.debug("Saving new employee: {}", employee.getName());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updated) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setDepartment(updated.getDepartment());

        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        logger.warn("Deleting employee with ID: {}", id);
        employeeRepository.deleteById(id);
    }



}
