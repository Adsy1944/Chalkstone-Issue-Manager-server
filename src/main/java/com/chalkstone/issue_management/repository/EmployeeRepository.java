package com.chalkstone.issue_management.repository;

import com.chalkstone.issue_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Gets all the Employees in the database
     * @return - List of Employees
     */
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    public ArrayList<Employee> getAllEmployees();

    /**
     * Gets a single Employee from their ID
     * @param id
     * @return - A single Employee
     */
    @Query(value = "SELECT * FROM employee WHERE id = :id", nativeQuery = true)
    public Employee getEmployeeById(@Param("id") Long id);
}
