package com.chalkstone.issue_management.controller;

import com.chalkstone.issue_management.model.Issue;
import com.chalkstone.issue_management.service.IssueService;
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
            List<Issue> issues = issueService.getAllIssues();
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
            Optional<Issue> issue = issueService.getIssueById(id);
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
            Issue result = issueService.addIssue(issue);
            if (result != null) {
                return ResponseEntity.ok().body(result);
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
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(issue);
                logger.info("issue to udpate:\n{}", json);
            } catch(Exception e) {
                logger.error("Could not parse JSON");
                logger.warn(e.getMessage());
            }
            Issue result = issueService.updateIssue(issue);

                return ResponseEntity.ok().body("Issue updated successfully");
//            } else {
//                throw new Exception("Failed to update issue");

        } catch(Exception e) {
            logger.error("Failed to update Issue: {}", issue.getId().toString());
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().body("Failed to update issue");
        }

    }
    
    @GetMapping(path="/getTriageIssues")
    public ResponseEntity<?> getTriageIssues() {
        try {
            logger.info("Getting Triage Issues");
            ArrayList<Issue> result = issueService.getTriageIssues();
            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
            logger.error("Failed to retrieve triage issues");
            logger.warn(e.getMessage());
        }
        return ResponseEntity.badRequest().body("Failed to retrieve triage issues");
    }
    
//    @GetMapping(path="/findIssuesByEmailOrLocation")
//    public ResponseEntity<?> findIssuesByEmailOrLocation(@RequestParam(name="email", required=false) String email, @RequestParam(name="location", required=false) String location) {
//        try {
//            logger.info("Trying to find issues relating to Email: {} and Location: {}", email, location);
//            List<Issue> result = issueService.findIssuesByEmailOrLocation(email, location);
//            return ResponseEntity.ok().body(result);
//        } catch(Exception e) {
//            logger.error("Could not find results: {}", e.getMessage());
//            return ResponseEntity.badRequest().body("Could not find results");
//        }
//    }
    
}
