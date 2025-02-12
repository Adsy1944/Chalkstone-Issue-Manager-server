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
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) {
        try {
            logger.info("Getting employee by id: {}", id);
            Employee response = employeeService.getEmployeeById(id);
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            logger.error("Could not get employee\n{}", e.getMessage());
            return ResponseEntity.badRequest().body("Could not get employee");
        }
    }

    @GetMapping(path="/getAllEmployees")
    public ResponseEntity<?> getAllEmployees() {
        try {
            logger.info("Get all employees");
            ArrayList<Employee> response = employeeService.getAllEmployees();
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            logger.error("Could not get all employees\n{}", e.getMessage());
            return ResponseEntity.badRequest().body("Could not get all employees");
        }
    }

    @PostMapping(path="/addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        try {
            String json = mapper.writeValueAsString(employee);
            logger.info("Adding employee:\n{}", json);
            if (employeeService.addEmployee(employee) == 1) {
                return ResponseEntity.ok().body(employee);
            } else {
                return ResponseEntity.badRequest().body("Could not add employee");
            }
        } catch(Exception e) {
            logger.error("Could not add employee\n{}", e.getMessage());
            return ResponseEntity.badRequest().body("Could not add employee");
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

}
