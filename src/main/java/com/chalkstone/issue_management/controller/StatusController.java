package com.chalkstone.issue_management.controller;

import com.chalkstone.issue_management.model.Status;
import com.chalkstone.issue_management.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Controller class to provide an endpoint for the UI to make requests
 */
@CrossOrigin
@Controller
@RequestMapping(path="/status")
public class StatusController {

    private final StatusService statusService;
    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(path="/getAllStatuses")
    public ResponseEntity<?> getAllStatuses() {
        try {
            logger.info("Getting all statuses");
            ArrayList<Status> statuses = statusService.getAllStatuses();
            return ResponseEntity.ok().body(statuses);
        } catch(Exception e) {
            logger.error("Could not retrieve statuses\n{}", e.getMessage());
        }
        return ResponseEntity.badRequest().body("Could not retrieve statuses");
    }

    @RequestMapping(path="/getStatusById/{id}")
    public ResponseEntity<?> getStatusById(@PathVariable("id") Long id) {
        try {
            logger.info("Getting status: {}", id);
            Status status = statusService.getStatusById(id);
            return ResponseEntity.ok().body(status);
        } catch(Exception e) {
            logger.error("Could not retrieve status\n{}", e.getMessage());
        }
        return ResponseEntity.badRequest().body("Could not retrieve status");
    }

}
