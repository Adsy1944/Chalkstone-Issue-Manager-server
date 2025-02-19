package com.chalkstone.issue_management.service;

import com.chalkstone.issue_management.model.Status;
import com.chalkstone.issue_management.repository.IssueRepository;
import com.chalkstone.issue_management.repository.StatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * * Service class to carry out any business logic on objects between endpoint and database
 * */
@Service
@Transactional
public class StatusService {

    /**
     * Constructor and configuration of class
     */
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    /**
     * Gets all Statuses from the database
     * @return - List of Statuses
     */
    public ArrayList<Status> getAllStatuses() {
        return statusRepository.getAllStatuses();
    }

    /**
     * Gets a single Status by its ID
     * @param id
     * @return - A single Status
     */
    public Status getStatusById(Long id) {
        return statusRepository.getStatusById(id);
    }

    public int updateStatus(Status status) {
        return statusRepository.updateStatus(status.getStatus(), status.getId());
    }

    public int deleteStatus(Long id) {
        return statusRepository.deleteStatus(id);
    }

    public int addStatus(Status status) {
        return statusRepository.addStatus(status.getStatus());
    }
}
