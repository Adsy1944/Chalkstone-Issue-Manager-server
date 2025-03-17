package com.chalkstone.issue_management.repository;

import com.chalkstone.issue_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    /**
     * Saves a new employee
     * @param employee must not be {@literal null}.
     * @return
     */
    Employee save(Employee employee);

    /**
     * Gets a single Employee from their ID
     * @param id
     * @return - A single Employee
     */
    Optional<Employee> findById(Long id);

    /**
     * Gets all Employees
     * @return
     */
    List<Employee> findAll();

    @Modifying
    @Query(value = "UPDATE employee SET firstName = :firstName, lastName = :lastName, role = :role WHERE id = :id", nativeQuery = true)
    int updateEmployee(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("role") String role, @Param("id") Long id);

    @Modifying
    @Query(value = "DELETE FROM employee WHERE id = :id", nativeQuery = true)
    int deleteEmployee(@Param("id") Long id);


}
