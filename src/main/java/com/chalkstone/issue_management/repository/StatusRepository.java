package com.chalkstone.issue_management.repository;

import com.chalkstone.issue_management.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface StatusRepository extends JpaRepository<Status, Long> {

    /**
     * Gets all the Employees in the database
     * @return - List of Statuses
     */
    @Query(value="SELECT * FROM status", nativeQuery = true)
    ArrayList<Status> getAllStatuses();

    /**
     * Gets a single Employee based on the ID provided
     * @param id
     * @return - A single Employee
     */
    @Query(value="SELECT * FROM status WHERE id = :id", nativeQuery = true)
    Status getStatusById(@Param("id") Long id);

    /*
    Currently the application is only intended to read the status table with no access to add normal status types,
    but if it was deemed a requirement to make changes to the data, then it would be added below
     */

    /**
     * Deletes an Issue from the database chosen by ID (should not be required)
     * @param id
     * @return Confirmation of a successful delete
     */
    @Modifying
    @Query(value="DELETE FROM status WHERE id = :id", nativeQuery = true)
    int deleteStatus(@Param("id") Long id);

    /**
     * Adds a new status to the database
     * @param status
     * @return Confirmation that was added successfully
     */
    @Modifying
    @Query(value="INSERT INTO status (status) VALUES (:status)", nativeQuery = true)
    int addStatus(@Param("status") String status);

    /**
     * Updates a status identified by its ID
     * @param status
     * @param id
     * @return Confirmation that was updated successfully
     */
    @Modifying
    @Query(value="UPDATE status SET status = :status WHERE id = :id", nativeQuery = true)
    int updateStatus(@Param("status") String status, @Param("id") Long id);

}
