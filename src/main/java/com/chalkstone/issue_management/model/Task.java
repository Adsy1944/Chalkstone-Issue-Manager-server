package com.chalkstone.issue_management.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "issueId")
    private Long issueId;

    @Column(name = "AssignedTo")
    private Long assignedTo;

    @Column(name = "location")
    private String location;

    @Column(name = "taskCategory")
    private Long taskCategory;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Long status;

    @Column(name = "completedDate")
    private Date completedDate;

}
