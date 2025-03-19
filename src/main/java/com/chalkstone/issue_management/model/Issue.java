package com.chalkstone.issue_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "issueCategory")
    private Long issueCategory;

//    @Column(name = "location")
    private String location;

//    @Column(name = "reportedDate")
    private Date reportedDate;

//    @Column(name = "description")
    private String description;

//    @Column(name = "status")
    private Long status;

//    @Column(name = "resolvedDate")
    private Date resolvedDate;

//    @JsonIgnore
//    @Column(name = "closedBy")
    private Long closedBy;

//    @Column(name = "customerName")
    private String customerName;

//    @Column(name = "customerEmail")
    private String customerEmail;

    public Issue(){}
    
    public Issue(Long id, Long issueCategory, String location, Date reportedDate, String description, Long status, Date resolvedDate, Long closedBy, String customerName, String customerEmail) {
        this.id = id;
        this.issueCategory = issueCategory;
        this.location = location;
        this.reportedDate = reportedDate;
        this.description = description;
        this.status = status;
        this.resolvedDate = resolvedDate;
        this.closedBy = closedBy;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public Issue(Long l, Long l1, String s, java.util.Date date, String pothole, Long l2, Object o, String obiwanKenobi, String s1) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIssueCategory() {
        return issueCategory;
    }

    public void setIssueCategory(Long issueCategory) {
        this.issueCategory = issueCategory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public Long getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(Long closedBy) {
        this.closedBy = closedBy;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
