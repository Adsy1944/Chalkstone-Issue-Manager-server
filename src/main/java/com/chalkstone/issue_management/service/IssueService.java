package com.chalkstone.issue_management.service;

import com.chalkstone.issue_management.model.Issue;
import com.chalkstone.issue_management.repository.IssueRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
@Transactional
public class IssueService {

        private final IssueRepository issueRepository;
        private static final Logger logger = LoggerFactory.getLogger(IssueService.class);

        public IssueService(IssueRepository issueRepository) {
            this.issueRepository = issueRepository;
        }

        public int addIssue(Issue issue) {
            return issueRepository.addIssue(
                    issue.getIssueCategory(),
                    issue.getLocation(),
                    issue.getDescription(),
                    issue.getCustomerName(),
                    issue.getCustomerEmail()
            );
        }

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

        public ArrayList<Issue> getAllIssues() {
            return issueRepository.getAllIssues();
        }

        public Issue getIssueById(Long id) {
            return issueRepository.getIssueById(id);
        }

}
