package com.chalkstone.issue_management.controller;

import com.chalkstone.issue_management.model.Status;
import com.chalkstone.issue_management.service.StatusService;
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
@RequestMapping(path="/status")
public class StatusController {

    private final StatusService statusService;
    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping(path="/getAllStatuses")
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

    @GetMapping(path="/getStatusById/{id}")
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

    @PostMapping(path="/addStatus")
    public ResponseEntity<?> addStatus(@RequestBody Status status) {
        try {
            logger.info("Adding status {}", status.getStatus());
            if (statusService.addStatus(status) == 1) {
                return ResponseEntity.ok().body("Status added successfully");
            } else {
                logger.info("Could not add status: {}", status.getStatus());
                return ResponseEntity.badRequest().body("Could not add status");
            }
        } catch(Exception e) {
            logger.error("Could not add status\n{}", e.getMessage());
            return ResponseEntity.badRequest().body("Could not add status");
        }
    }

    @PutMapping(path="/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody Status status) {
        try {
            logger.info("Updating status: {}", status);
            if (statusService.updateStatus(status) == 1) {
                return ResponseEntity.ok().body("Status updated succesfully");
            }
        } catch(Exception e) {
            logger.error("Could not update status:\n{}", e.getMessage());
        }
        return ResponseEntity.badRequest().body("Failed to update status");
    }

    @DeleteMapping(path="/deleteStatus/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable("id") Long id) {
        try {
            if (statusService.deleteStatus(id) == 1) {
                return ResponseEntity.ok().body("Status deleted successfully");
            }
        } catch(Exception e) {
            logger.error("Could not delete status:\n{}", e.getMessage());
        }
        return ResponseEntity.badRequest().body("Could not delete status");
    }

}
