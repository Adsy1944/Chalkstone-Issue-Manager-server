package com.chalkstone.issue_management.employee;

import com.chalkstone.issue_management.model.Employee;
import com.chalkstone.issue_management.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeRepositoryTest.class);
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployeeById() {
        // Arrange
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setRole("Developer");
        entityManager.persist(employee);
        entityManager.flush();

        // Act
        Employee result = employeeRepository.findById(employee.getId()).orElse(null);

        // Log for debugging
        logger.info("Persisted Employee ID: " + employee.getId());
        logger.info("Retrieved Employee: " + result);

        // Assert
        assertNotNull(result);
        assertEquals("Developer", result.getRole());
    }

    @Test
    public void testAddEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setRole("Developer");

        // Act
        Employee savedEmployee = employeeRepository.save(employee);

        // Assert
        assertNotNull(savedEmployee.getId());
        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("Doe", savedEmployee.getLastName());
        assertEquals("Developer", savedEmployee.getRole());
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setRole("Developer");
        entityManager.persist(employee);
        Employee employee2 = new Employee();
        employee.setFirstName("Bruce");
        employee.setLastName("Willis");
        employee.setRole("Admin");
        entityManager.persist(employee2);
        entityManager.flush();

        //Act
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee2);
        List<Employee> employeesResult = employeeRepository.findAll();

        //Assert
        assertEquals(employees, employeesResult);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Wick");
        employee.setRole("Highways");
        try {
            String json = mapper.writeValueAsString(employee);
            logger.info("Employee:\n" + json);
        } catch(Exception e) {
            logger.error("Could not parse JSON");
        }

        Employee savedEmployee = employeeRepository.save(employee);
        try {
            String json = mapper.writeValueAsString(employee);
            logger.info("savedEmployee:\n" + json);
        } catch(Exception e) {
            logger.error("Could not parse JSON");
        }

        Employee update = new Employee();
        update.setId(employee.getId());
        update.setFirstName("John");
        update.setLastName("Shephard");
        update.setRole("Highways");

        try {
            String json = mapper.writeValueAsString(update);
            logger.info("Update:\n" + json);
        } catch(Exception e) {
            logger.error("Could not parse JSON");
        }

        //Act
        Employee updatedEmployee = employeeRepository.save(update);
        try {
            String json = mapper.writeValueAsString(update);
            logger.info("UpdatedEmployee:\n" + json);
        } catch(Exception e) {
            logger.error("Could not parse JSON");
        }

        //Assert
        assertNotNull(employee);
        assertNotNull(update);
        assertNotNull(updatedEmployee);
        logger.info("\nFirst Name\nInitial: {}, Update: {}, Final: {}", employee.getFirstName(), update.getFirstName(), updatedEmployee.getFirstName());
        assertEquals(updatedEmployee.getFirstName(), update.getFirstName());
        logger.info("\nLast Name\nInitial: {}, Update: {}, Final: {}", employee.getLastName(), update.getLastName(), updatedEmployee.getLastName());
        assertEquals(updatedEmployee.getLastName(), update.getLastName());
        logger.info("\nRole\nInitial: {}, Update: {}, Final: {}", employee.getRole(), update.getRole(), updatedEmployee.getRole());
        assertEquals(updatedEmployee.getRole(), update.getRole());
    }

    @Test
    public void testDeleteEmployee() {

    }
}
