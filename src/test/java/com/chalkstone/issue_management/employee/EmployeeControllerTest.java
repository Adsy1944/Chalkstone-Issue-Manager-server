package com.chalkstone.issue_management.employee;

import com.chalkstone.issue_management.controller.EmployeeController;
import com.chalkstone.issue_management.model.Employee;
import com.chalkstone.issue_management.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockitoBean
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Long id = 1L;
        Employee employee = new Employee(id, "John", "Doe", "Developer");
        when(employeeService.getEmployeeById(id)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/employee/getEmployeeById/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.role").value("Developer"));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", "Developer"));
        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employee/getAllEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].role").value("Developer"));
    }

    @Test
    public void testAddEmployee() throws Exception {
        Employee employee = new Employee(1L, "John", "Doe", "Developer");
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);

        ObjectMapper objectMapper = new ObjectMapper();
        String employeeJson = objectMapper.writeValueAsString(employee);

        mockMvc.perform(post("/employee/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.role").value("Developer"));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee(1L, "John", "Doe", "Developer");
        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String employeeJson = objectMapper.writeValueAsString(employee);

        mockMvc.perform(put("/employee/updateEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.role").value("Developer"));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        Long id = 1L;
        when(employeeService.deleteEmployee(id)).thenReturn(1);

        mockMvc.perform(delete("/employee/deleteEmployee/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee deleted successfully"));
    }

}
