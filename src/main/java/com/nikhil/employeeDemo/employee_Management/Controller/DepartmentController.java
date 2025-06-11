package com.nikhil.employeeDemo.employee_Management.Controller;

import com.nikhil.employeeDemo.employee_Management.Model.Department;
import com.nikhil.employeeDemo.employee_Management.Service.DepartmentServiceImpl;
import com.nikhil.employeeDemo.employee_Management.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        if (departments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No departments found.");
        }
        return ResponseEntity.ok(departments);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id) {
        try {
            Department department = departmentService.getDepartmentById(id);
            return ResponseEntity.ok(department);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Department not found with ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        try {
            if (department.getName() == null || department.getName().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Department name cannot be null or empty.");
            }

            if (department.getId() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("ID should not be provided while creating a department.");
            }

            Department created = departmentService.createDepartment(department);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create department: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, @RequestBody Department updated) {
        try {
            Department result = departmentService.updateDepartment(id, updated);
            return ResponseEntity.ok(result);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Department not found with ID: " + id);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update department with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.deleteDepartment(id);
            return ResponseEntity.ok("Department deleted successfully.");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Department not found with ID: " + id);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete department with ID: " + id);
        }
    }
}
