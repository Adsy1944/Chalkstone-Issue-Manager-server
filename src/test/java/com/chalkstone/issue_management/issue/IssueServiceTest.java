package com.chalkstone.issue_management.issue;

import com.chalkstone.issue_management.employee.EmployeeServiceTest;
import com.chalkstone.issue_management.model.Issue;
import com.chalkstone.issue_management.repository.IssueRepository;
import com.chalkstone.issue_management.service.IssueService;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class IssueServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(IssueServiceTest.class);
    ObjectMapper mapper = new ObjectMapper();

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueService issueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetIssueById() {
        Issue issue = new Issue(1L, 2L, "red.shirts.matter", Date.valueOf("2025-02-10"), "Pothole", 3L, null, "Obiwan Kenobi", "okenobi@thejediorder.crsnt");

        when(issueRepository.findById(1L)).thenReturn(Optional.of(issue));

        Optional<Issue> result = issueService.getIssueById(1L);
        assertTrue(result.isPresent());
        assertEquals(issue, result.get());
    }

    @Test
    public void testGetAllIssues() {
        Issue issue = new Issue(1L, 2L, "red.shirts.matter", Date.valueOf("2025-02-26"), "Pothole", 3L, null, "Obiwan Kenobi", "okenobi@thejediorder.crsnt");
        Issue issue2 = new Issue(2L, 2L, "blue.do.science", Date.valueOf("2025-02-27"), "Graffiti", 3L, null, "Quigon Gin", "qgin@thejediorder.crsnt");
        ArrayList<Issue> issues = new ArrayList<>();
        issues.add(issue);
        issues.add(issue2);

        when(issueRepository.getAllIssues()).thenReturn(issues);

        List<Issue> result = issueService.getAllIssues();
        assertEquals(result, issues);
    }

    @Test
    public void testAddIssue() {
        Issue issue = new Issue(1L, 2L, "red.shirts.matter", Date.valueOf("2025-02-26"), "Pothole", 3L, null, "Obiwan Kenobi", "okenobi@thejediorder.crsnt");
        when(issueRepository.save(issue)).thenReturn(issue);
        Issue result = issueRepository.save(issue);
        assertEquals(issue, result);
    }

    @Test
    public void testUpdateIssue() {
        Issue issue = new Issue(1L, 2L, "red.shirts.matter", Date.valueOf("2025-02-26"), "Pothole", 3L, null, "Obiwan Kenobi", "okenobi@thejediorder.crsnt");
        when(issueRepository.save(issue)).thenReturn(issue);
        Issue result = issueRepository.save(issue);

        assertEquals(issue, result);
    }

    @Test
    public void testDeleteIssue() {
        Issue issue = new Issue(1L, 2L, "red.shirts.matter", Date.valueOf("2025-02-26"), "Pothole", 3L, null, "Obiwan Kenobi", "okenobi@thejediorder.crsnt");
        when(issueRepository.deleteIssue(issue.getId())).thenReturn(1);

        int result = issueRepository.deleteIssue(issue.getId());
        assertEquals(1, result);
    }

}
