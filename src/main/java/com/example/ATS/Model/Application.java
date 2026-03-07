package com.example.ATS.Model;

import com.example.ATS.Enum.Status;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"candidate_id", "job_id"})
)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Users candidate;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String resumeUrl;
    private Date appliedAt;
    private Date lastUpdated;

    public Application() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getCandidate() {
        return candidate;
    }

    public void setCandidate(Users candidate) {
        this.candidate = candidate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public Date getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(Date appliedAt) {
        this.appliedAt = appliedAt;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
