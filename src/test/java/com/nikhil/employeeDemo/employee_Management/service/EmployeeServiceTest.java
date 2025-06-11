package com.nikhil.employeeDemo.employee_Management.service;

import com.nikhil.employeeDemo.employee_Management.Model.Department;
import com.nikhil.employeeDemo.employee_Management.Model.Employee;
import com.nikhil.employeeDemo.employee_Management.Service.EmployeeServiceImpl;
import com.nikhil.employeeDemo.employee_Management.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testGetEmployeeById_WhenEmployeeExists() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Nikhil");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Act
        Employee result = employeeService.getEmployeeById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Nikhil", result.getName());
        verify(employeeRepository, times(1)).findById(1L);

    }

    @Test
    void testGetEmployeeById_WhenEmployeeNotFound() {
        // Arrange
        when(employeeRepository.findById(100L)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> employeeService.getEmployeeById(100L)
        );

        assertEquals("Employee with ID 100 not found", thrown.getMessage());
        verify(employeeRepository, times(1)).findById(100L);
    }

    @Test
    void testCreateEmployee_SuccessfullySaves() {
        // Arrange
        Employee employee = new Employee();
        employee.setName("Nikhil");

        Department department = new Department();
        department.setId(1L);
        department.setName("Engineering");

        employee.setDepartment(department);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        Employee result = employeeService.createEmployee(employee);

        // Assert
        assertNotNull(result);
        assertEquals("Nikhil", result.getName());
        assertEquals("Engineering", result.getDepartment().getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetAllEmployees_ReturnsListOfEmployees() {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        Employee emp1 = new Employee(1L, "Alice", "alice@example.com", department);
        Employee emp2 = new Employee(2L, "Bob", "bob@example.com", department);

        List<Employee> employeeList = List.of(emp1, emp2);

        when(employeeRepository.findAll()).thenReturn(employeeList);

        // Act
        List<Employee> result = employeeService.getAllEmployees();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEmployee_SuccessfullyUpdates() {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("Engineering");

        Employee existing = new Employee(1L, "Old Name", "old@mail.com", department);
        Employee updated = new Employee(null, "New Name", "new@mail.com", department);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Employee result = employeeService.updateEmployee(1L, updated);

        // Assert
        assertEquals("New Name", result.getName());
        assertEquals("new@mail.com", result.getEmail());

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(existing);
    }

    @Test
    void testDeleteEmployee_SuccessfullyDeletes() {
        // Arrange
        Long id = 1L;
        doNothing().when(employeeRepository).deleteById(id);

        // Act
        employeeService.deleteEmployee(id);

        // Assert
        verify(employeeRepository, times(1)).deleteById(id);
    }
}
