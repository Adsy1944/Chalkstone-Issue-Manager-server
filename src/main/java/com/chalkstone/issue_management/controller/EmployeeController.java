package com.chalkstone.issue_management.controller;

import com.chalkstone.issue_management.model.Employee;
import com.chalkstone.issue_management.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller class to provide an endpoint for the UI to make requests
 */
@CrossOrigin
@Controller
@RequestMapping(path="/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    ObjectMapper mapper = new ObjectMapper();

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path="/getEmployeeById/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable("id") Long id) {
        try {
            logger.info("Getting employee by id: {}", id);
            Optional<Employee> response = employeeService.getEmployeeById(id);
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            logger.error("Could not get employee\n{}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(path="/getAllEmployees")
    public ResponseEntity<?> getAllEmployees() {
        try {
            logger.info("Get all employees");
            List<Employee> response = employeeService.getAllEmployees();
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            logger.error("Could not get all employees\n{}", e.getMessage());
            return ResponseEntity.badRequest().body("Could not get all employees");
        }
    }

    @PostMapping(path="/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            String json = mapper.writeValueAsString(employee);
            logger.info("Adding employee:\n{}", json);
            Employee response = employeeService.addEmployee(employee);
            if (response != null) {
                return ResponseEntity.ok().body(employee);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch(Exception e) {
            logger.error("Could not add employee\n{}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping(path="/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        try {
            logger.info("Updating employee: {}", employee.getId());
            if (employeeService.updateEmployee(employee) == 1) {
                return ResponseEntity.ok().body(employee);
            } else {
                return ResponseEntity.badRequest().body("Could not update employee");
            }
        } catch(Exception e) {
            logger.error("Could not update employee\n{}", e.getMessage());
            return ResponseEntity.badRequest().body("Could not add employee");
        }
    }

    @DeleteMapping(path="/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        try {
            logger.info("Deleting employee: {}", id);
            if (employeeService.deleteEmployee(id) == 1) {
                return ResponseEntity.ok().body("Employee deleted successfully");
            } else {
                return ResponseEntity.badRequest().body("Could not delete employee");
            }
        } catch(Exception e) {
            logger.error("Could not delete employee {}", id);
            return ResponseEntity.badRequest().body("Could not delete employee");
        }
    }
}
