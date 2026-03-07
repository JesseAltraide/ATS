package com.example.ATS.DTO;

import com.example.ATS.Model.Job;

public class ApplicationDTO {

    private String resumeUrl;
    private Job job;

    public ApplicationDTO() {
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
