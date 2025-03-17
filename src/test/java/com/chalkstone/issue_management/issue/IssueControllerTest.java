package com.chalkstone.issue_management.issue;

import com.chalkstone.issue_management.controller.IssueController;
import com.chalkstone.issue_management.model.Issue;
import com.chalkstone.issue_management.service.IssueService;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(IssueController.class)
public class IssueControllerTest {

    @MockitoBean
    private IssueService issueService;

    @InjectMocks
    IssueController issueController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
    }

    @Test
    public void testGetIssueById() throws Exception {
        Long id = 1L;
        Issue issue = new Issue(1L, 2L, "red.shirts.matter", Date.valueOf("2025-02-26"), "Pothole", 3L, null, null, "Obiwan Kenobi", "okenobi@thejediorder.crsnt");
        when(issueService.getIssueById(id)).thenReturn(Optional.of(issue));

        mockMvc.perform(get("/issue/getIssueById/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.location").value(issue.getLocation()))
                .andExpect(jsonPath("$.customerEmail").value(issue.getCustomerEmail()));

    }

    @Test
    public void testGetAllIssues() throws Exception {
        Issue issue = new Issue(1L, 2L, "red.shirts.matter", Date.valueOf("2025-02-26"), "Pothole", 3L, null, null, "Obiwan Kenobi", "okenobi@thejediorder.crsnt");
        Issue issue2 = new Issue(2L, 2L, "blue.do.science", Date.valueOf("2025-02-27"), "Graffiti", 3L, null, null, "Quigon Gin", "qgin@thejediorder.crsnt");
        List<Issue> issueList = new ArrayList<>();
        issueList.add(issue);
        issueList.add(issue2);

        when(issueService.getAllIssues()).thenReturn(issueList);

        mockMvc.perform(get("/issue/getAllIssues"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(issue.getId()))
                .andExpect(jsonPath("$[0].location").value(issue.getLocation()))
                .andExpect(jsonPath("$[1].id").value(issue2.getId()))
                .andExpect(jsonPath("$[1].location").value(issue2.getLocation()));

    }

    @Test
    public void testCreateIssue() throws Exception {
        Issue issue = new Issue(1L, 2L, "red.shirts.matter", Date.valueOf("2025-02-26"), "Pothole", 3L, null, null, "Obiwan Kenobi", "okenobi@thejediorder.crsnt");
        when(issueService.addIssue(any(Issue.class))).thenReturn(issue);

        ObjectMapper objectMapper = new ObjectMapper();
        String issueJson = objectMapper.writeValueAsString(issue);

        mockMvc.perform(post("/issue/createIssue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(issueJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(issue.getId()))
                .andExpect(jsonPath("$.location").value(issue.getLocation()));

    }


}
