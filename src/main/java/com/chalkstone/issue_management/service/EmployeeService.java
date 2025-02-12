package com.chalkstone.issue_management.service;

import com.chalkstone.issue_management.model.Employee;
import com.chalkstone.issue_management.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    public ArrayList<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public int addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee.getName(), employee.getRole());
    }

    public int updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee.getName(), employee.getRole(), employee.getId());
    }


}
