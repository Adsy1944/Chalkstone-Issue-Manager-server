package com.chalkstone.issue_management.employee;

import com.chalkstone.issue_management.model.Employee;
import com.chalkstone.issue_management.repository.EmployeeRepository;
import com.chalkstone.issue_management.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceTest.class);
    ObjectMapper mapper = new ObjectMapper();

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEmployeeById() {
    Long id = 1L;
    Employee employee = new Employee();
    employee.setId(id);
    employee.setFirstName("William");
    employee.setLastName("Riker");
    employee.setRole("Admin");
    when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

    Optional<Employee> result = employeeService.getEmployeeById(id);
    assertTrue(result.isPresent());
    assertEquals(employee, result.get());
    }

    @Test
    public void testGetAllEmployees() {

        Employee employee = new Employee();
        employee.setId(2L);
        employee.setFirstName("Deanna");
        employee.setLastName("Troy");
        employee.setRole("Admin");

        Employee employee2 = new Employee();
        employee.setId(3L);
        employee.setFirstName("Geordi");
        employee.setLastName("Laforge");
        employee.setRole("Engineer");

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(employees, result);

    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("William");
        employee.setLastName("Riker");
        employee.setRole("Admin");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.addEmployee(employee);
        assertEquals(employee, result);
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("William");
        employee.setLastName("Riker");
        employee.setRole("Admin");

        when(employeeRepository.updateEmployee(anyString(), anyString(), anyString(), anyLong())).thenReturn(1);

        int result = employeeService.updateEmployee(employee);
        assertEquals(1, result);

    }
}
