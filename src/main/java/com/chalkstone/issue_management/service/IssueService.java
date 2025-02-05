package com.chalkstone.issue_management.service;

import com.chalkstone.issue_management.model.Issue;
import com.chalkstone.issue_management.repository.IssueRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

/**
 * * Service class to carry out any business logic on objects between endpoint and database
 * */
@Service
@Transactional
public class IssueService {


    /**
     * Constructor and configuration of class
     */
    private final IssueRepository issueRepository;
    private static final Logger logger = LoggerFactory.getLogger(IssueService.class);

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    /**
     * Creates a new issue in the database, including breaking down the passed in Issue to it's attributes
     * @param issue - The new issue to be stored
     * @return - Confirmation that the request was successful
     */
    public int addIssue(Issue issue) {
            return issueRepository.addIssue(
                    issue.getIssueCategory(),
                    issue.getLocation(),
                    issue.getDescription(),
                    issue.getCustomerName(),
                    issue.getCustomerEmail()
            );
        }

    /**
     * Updates an issue by replacing the content with the Issue provided
     * @param issue - To replace the current data
     * @return - Confirmation that the request was successful
     */
    public int updateIssue(Issue issue) {
            return issueRepository.updateIssue(
                    issue.getIssueCategory(),
                    issue.getLocation(),
                    issue.getReportedDate(),
                    issue.getDescription(),
                    issue.getStatus(),
                    issue.getResolvedDate(),
                    issue.getClosedBy(),
                    issue.getId()
            );
        }

    /**
     * Gets all issues stored
     * @return - An Array of Issues
     */
    public ArrayList<Issue> getAllIssues() {
        return issueRepository.getAllIssues();
    }

    /**
     * Gets a specific issue by its ID
     * @param id - ID of the requested issue
     * @return - A single Issue
     */
    public Issue getIssueById(Long id) {
        return issueRepository.getIssueById(id);
    }

}
