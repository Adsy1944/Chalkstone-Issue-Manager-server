package com.chalkstone.issue_management.repository;

import com.chalkstone.issue_management.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.ArrayList;

public interface IssueRepository extends JpaRepository<Issue, Long> {

//  Create new Issue
    @Modifying
    @Query(value="INSERT INTO issue (issueCategory, location, reportedDate, description, status, customerName, customerEmail)" +
            " VALUES (:issueCategory, :location, GETDATE(), :description, 'pending', :customerName, :customerEmail);", nativeQuery = true)
    int addIssue(@Param("issueCategory") Long issueCategory, @Param("location") String location, @Param("description") String description,
                 @Param("customerName") String customerName, @Param("customerEmail") String customerEmail);

//  Update and existing Issue
    @Modifying
    @Query(value="UPDATE issue SET issueCategory = :issueCategory, location = :location, reportedDate = :reportedDate, " +
            "description = :description, status = :status, resolvedDate = :resolvedDate, closedBy = :closedBy WHERE id = :id", nativeQuery = true)
    int updateIssue(@Param("issueCategory") Long issueCategory, @Param("location") String location,
                    @Param("reportedDate") Date reportedDate, @Param("description") String description, @Param("status") Long status,
                    @Param("resolvedDate") Date resolvedDate, @Param("closedBy") Long closedBy, @Param("id") Long id);

//  Delete
    @Modifying
    @Query(value="DELETE FROM issue WHERE id = :id", nativeQuery = true)
    int deleteIssue(@Param("id") Long id);

////  Cancel or close - Set status ID to match cancelled
//    @Modifying
//    @Query(value="UPDATE issue SET closedBy = :closedBy, status = :status WHERE id = :id", nativeQuery = true)
//    int updateIssueStatus(@Param("closedBy") Long closedBy, @Param("status") Long status, @Param("id") Long id);

//  Read
    @Query(value="SELECT * FROM issue", nativeQuery = true)
    ArrayList<Issue> getAllIssues();

    @Query(value="SELECT * FROM issue WHERE id = :id", nativeQuery = true)
    Issue getIssueById(@Param("id") Long id);

}
