package com.chalkstone.issue_management.service;

import com.chalkstone.issue_management.model.Issue;
import com.chalkstone.issue_management.repository.IssueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

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

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    /**
     * Creates a new issue in the database, including breaking down the passed in Issue to it's attributes
     * @param issue - The new issue to be stored
     * @return - Confirmation that the request was successful
     */
    public Issue addIssue(Issue issue) {
        LocalDate localDate = LocalDate.now();
            issue.setStatus(1L);
            issue.setReportedDate(Date.valueOf(localDate));
            return issueRepository.save(issue);
        }

    /**
     * Updates an issue by replacing the content with the Issue provided
     * @param issue - To replace the current data
     * @return - Confirmation that the request was successful
     */
    public Issue updateIssue(Issue issue) {
            return issueRepository.save(issue);
        }

    /**
     * Gets all issues stored
     * @return - An Array of Issues
     */
    public List<Issue> getAllIssues() {
        return issueRepository.getAllIssues();
    }

    /**
     * Gets a specific issue by its ID
     *
     * @param id - ID of the requested issue
     * @return - A single Issue
     */
    public Optional<Issue> getIssueById(Long id) {
        return issueRepository.findById(id);
    }

    /**
     * Deletes a specific issue by its ID
     * @param id
     * @return - Confirmation that the deletion was successful
     */
    public int deleteIssueById(Long id) { return issueRepository.deleteIssue(id); }

    public List<Issue> findIssuesByEmailOrLocation(String email, String location) {
        return issueRepository.findIssuesByEmailOrLocation(email, location);
    }

}
