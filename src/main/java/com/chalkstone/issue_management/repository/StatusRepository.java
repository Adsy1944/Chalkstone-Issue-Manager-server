package com.chalkstone.issue_management.repository;

import com.chalkstone.issue_management.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface StatusRepository extends JpaRepository<Status, Long> {

    /**
     * Gets all the Employees in the database
     * @return - List of Statuses
     */
    @Query(value="SELECT * FROM status", nativeQuery = true)
    public ArrayList<Status> getAllStatuses();

    /**
     * Gets a single Employee based on the ID provided
     * @param id
     * @return - A single Employee
     */
    @Query(value="SELECT * FROM status WHERE id = :id", nativeQuery = true)
    public Status getStatusById(@Param("id") Long id);

    /*
    Currently the application is only intended to read the status table with no access to add normal status types,
    but if it was deemed a requirement to make changes to the data, then it would be added below
     */

}
