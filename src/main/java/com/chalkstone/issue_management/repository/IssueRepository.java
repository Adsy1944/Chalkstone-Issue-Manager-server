package com.chalkstone.issue_management.repository;

import com.chalkstone.issue_management.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository to connect and store/retrieve data from Issue table in database
 */
public interface IssueRepository extends JpaRepository<Issue, Long> {

//    /**
//     * Create a new issue in the database from the passed in attributes
//     * @param issueCategory
//     * @param location
//     * @param description
//     * @param customerName
//     * @param customerEmail
//     * @return - Confirmation of a successful insert
//     */
//    @Modifying
//    @Query(value="INSERT INTO issue (issueCategory, location, reportedDate, description, status, customerName, customerEmail)" +
//            " VALUES (:issueCategory, :location, GETDATE(), :description, 'pending', :customerName, :customerEmail);", nativeQuery = true)
//    int addIssue(@Param("issueCategory") Long issueCategory, @Param("location") String location, @Param("description") String description,
//                 @Param("customerName") String customerName, @Param("customerEmail") String customerEmail);

    Issue save(Issue issue);

    /**
     * Deletes an Issue from the database chosen by ID (should not be required)
     * @param id
     * @return Confirmation of a successful delete
     */
    @Modifying
    @Query(value="DELETE FROM issue WHERE id = :id", nativeQuery = true)
    int deleteIssue(@Param("id") Long id);

    /**
     * Returns all saved Issues
     * @return - ArrayList of Issues
     */
    @Query(value="SELECT * FROM issue", nativeQuery = true)
    List<Issue> getAllIssues();

    /**
     * Gets a single issue based on its ID
     * @param id
     * @return - A single Issue
     */
    @Query(value="SELECT * FROM issue WHERE id = :id", nativeQuery = true)
    Issue getIssueById(@Param("id") Long id);

    ////  Cancel or close - Set status ID to match cancelled
//    @Modifying
//    @Query(value="UPDATE issue SET closedBy = :closedBy, status = :status WHERE id = :id", nativeQuery = true)
//    int updateIssueStatus(@Param("closedBy") Long closedBy, @Param("status") Long status, @Param("id") Long id);

    @Query(value="SELECT * FROM issue WHERE status = 1", nativeQuery = true)
    ArrayList<Issue> getTriageIssues();

    @Query(value="SELECT * FROM issue WHERE status != 1", nativeQuery = true)
    ArrayList<Issue> getNonTriageIssues();

    @Query(value="SELECT * FROM issue WHERE location = :location OR customer_email = :email", nativeQuery = true)
    List<Issue> findIssuesByEmailOrLocation(@Param("email") String email, @Param("location") String location);

}
