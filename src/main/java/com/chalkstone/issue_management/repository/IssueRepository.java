package com.chalkstone.issue_management.repository;

import com.chalkstone.issue_management.model.Issue;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface IssueRepository {

//  Create new Issue
    @Modifying
    @Query(value="INSERT INTO issue (issueCategory, location, reportedDate, description, status, customerName, customerEmail)" +
            " VALUES (:issueCategory, :location, GETDATE(), :description, 'pending', :customerName, :customerEmail);", nativeQuery = true)
    int addIssue(@Param("issueCategory") String issueCategory, @Param("location") String location, @Param("description") String description,
                 @Param("status") String status, @Param("customerName") String customerName, @Param("customerEmail") String customerEmail);

//  Update and existing Issue
    @Modifying
    @Query(value="UPDATE issue SET issueCategory = :issueCategory, location = :location, reportedDate = :reportedDate, " +
            "description = :description, status = :status, resolvedDate = :resolvedDate, closedBy = :closedBy WHERE id = :id", nativeQuery = true)
    int updateIssue(@Param("issueCategory") String issueCategory, @Param("location") String location,
                    @Param("reportedDate") Date reportedDate, @Param("description") String description, @Param("status") String status,
                    @Param("resolvedDate") Date resolvedDate, @Param("closedBy") String closedBy, @Param("id") Long id);

//  Delete
    @Modifying
    @Query(value="DELETE FROM issue WHERE id = :id", nativeQuery = true)
    int deleteIssue(@Param("id") Long id);

//  Read
    @Query(value="SELECT * FROM issue", nativeQuery = true)
    Issue[] getAllIssues();

    @Query(value="SELECT * FROM issue WHERE id = :id", nativeQuery = true)
    Issue getIssueById(@Param("id") Long id);

}
