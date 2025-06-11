package com.nikhil.employeeDemo.employee_Management.Service;

import jakarta.persistence.EntityNotFoundException;
import com.nikhil.employeeDemo.employee_Management.Model.Department;
import com.nikhil.employeeDemo.employee_Management.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhil.employeeDemo.employee_Management.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        logger.debug("Retrieving all departments from database");
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id){
        logger.debug("Retrieving department with ID: {}", id);
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    @Override
    public Department createDepartment(Department department) {
        logger.debug("Saving new department: {}", department.getName());
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Long id, Department updated) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department not found with ID: " + id);
        }
        logger.debug("Updating department with ID: {}", id);
        updated.setId(id);
        return departmentRepository.save(updated);
    }

    @Override
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department not found with ID: " + id);
        }
        logger.warn("Deleting department with ID: {}", id);
        departmentRepository.deleteById(id);
    }
}
