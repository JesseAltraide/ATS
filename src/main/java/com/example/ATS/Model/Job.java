package com.example.ATS.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String jobTitle;
    private String jobDescription;
    private String jobType;
    private Boolean isActive;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Users recruiter;

    public Job() {
    }

    public Integer getJobId() {
        return id;
    }

    public void setJobId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Users getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Users recruiter) {
        this.recruiter = recruiter;
    }
}
