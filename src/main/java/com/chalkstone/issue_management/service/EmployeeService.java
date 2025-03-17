package com.chalkstone.issue_management.service;

import com.chalkstone.issue_management.model.Employee;
import com.chalkstone.issue_management.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * * Service class to carry out any business logic on objects between endpoint and database
 * */
@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public int updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee.getFirstName(), employee.getLastName(), employee.getRole(), employee.getId());
    }

    public int deleteEmployee(Long id) {
        return employeeRepository.deleteEmployee(id);
    }


}
