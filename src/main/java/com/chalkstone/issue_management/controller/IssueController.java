package com.chalkstone.issue_management.controller;

import com.chalkstone.issue_management.model.Issue;
import com.chalkstone.issue_management.service.IssueService;
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
@RequestMapping(path="/issue")
public class IssueController {

    private final IssueService issueService;
    private static final Logger logger = LoggerFactory.getLogger(IssueController.class);

    public IssueController(IssueService issueService) {
    this.issueService = issueService;
}

    /**
     * Gets all the issues stored in the database
     * @return - All Issues
     */
    @GetMapping(path="/getAllIssues")
    public ResponseEntity<?> getAllIssues() {
        try {
            logger.info("Get all issues");
            ArrayList<Issue> issues = issueService.getAllIssues();
            return ResponseEntity.ok().body(issues);
        } catch(Exception e) {
            logger.error("Failed to retrieve all issues\n{}", e.getMessage());
        }
        return ResponseEntity.badRequest().body("Failed to retrieve all issues");
    }

    /**
     * Gets a particular issue based on it's ID
     * @param id - ID of the Issue
     * @return - The requested Issue
     */
    @GetMapping(path="/getIssueById/{id}")
    public ResponseEntity<?> getIssueById(@PathVariable("id") Long id) {
        try {
            logger.info("Get issue by ID: {}", id);
            Issue issue = issueService.getIssueById(id);
            return ResponseEntity.ok().body(issue);
        } catch(Exception e) {
            logger.error("Failed to retrieve issue\n{}", e.getMessage());
        }
        return ResponseEntity.badRequest().body("Failed to retrieve issue");
    }

    /**
     * Creates a new issue
     * @param issue - The new issue to be stored
     * @return - Confirmation that the request was successful
     */
    @PostMapping(path="/createIssue")
    public ResponseEntity<?> createIssue(@RequestBody Issue issue) {
        try {
            logger.info("Creating issue:\n{}", issue);
            int result = issueService.addIssue(issue);
            if (result == 1) {
                return ResponseEntity.ok().body("Issue created Successfully");
            }
            else {
                throw new Exception("Failed to create issue");
            }
        } catch(Exception e) {
            logger.error("Could not create Issue:\n{}\n{}", issue.getId().toString(), e.getMessage());
        }
        return ResponseEntity.badRequest().body("Failed to create issue");
    }

    /**
     * Updates an existing Issue
     * @param issue - The new issue update
     * @return - Confrimation that the request was successful
     */
    @PutMapping(path="/updateIssue")
    public ResponseEntity<?> updateIssue(@RequestBody Issue issue) {
        try {
            logger.info("Updating issue: {}", issue.getId());
            int result = issueService.updateIssue(issue);
            if (result == 1) {
                return ResponseEntity.ok().body("Issue updated successfully");
            } else {
                throw new Exception("Failed to update issue");
            }
        } catch(Exception e) {
            logger.error("Failed to update Issue: {}", issue.getId().toString());
        }
        return ResponseEntity.badRequest().body("Failed to update issue");
    }



}
