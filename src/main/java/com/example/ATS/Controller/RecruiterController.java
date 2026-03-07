package com.example.ATS.Controller;

import com.example.ATS.DTO.ChangeStatus;
import com.example.ATS.DTO.JobDTO;
import com.example.ATS.Model.Application;
import com.example.ATS.Model.Job;
import com.example.ATS.Service.ApplicationService;
import com.example.ATS.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.awt.datatransfer.StringSelection;
import java.util.List;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @Autowired
    JobService jobService;

    @Autowired
    ApplicationService applicationService;

    @PostMapping("/create/job")
    public ResponseEntity<String> createJob(@RequestBody JobDTO job, Authentication authentication) {

        return jobService.createJob(job, authentication);

    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<List<Job>> getAllPostedJobs(Authentication authentication) {

        return jobService.viewAllJobsPosted(authentication);

    }

    @GetMapping("/getAllApplications")
    public ResponseEntity<List<Application>> getAllApplications(@RequestParam Integer id ,Authentication authentication) {
        return applicationService.getApplications(id, authentication);
    }

    @GetMapping("/getUserApplication")
    public ResponseEntity<Application> getApplicationByUserAndJobId(@RequestParam Integer candidateId, @RequestParam Integer jobId, Authentication authentication) {
        return applicationService.getApplicationById(candidateId, jobId);
    }

    @PutMapping("/job/{jobId}/application/{applicationId}/status")
    public ResponseEntity<String> changeApplicantsStatus(@PathVariable Integer jobId , @PathVariable Integer applicationId,@RequestBody ChangeStatus changeStatus, Authentication authentication) {
        return applicationService.changeStatus(jobId, applicationId, changeStatus, authentication);
    }

    @PutMapping("/job/{jobId}/isActive")
    public ResponseEntity<String> changeJobStatus(@PathVariable Integer jobId, Boolean isActive, Authentication authentication) {
        return applicationService.changeJobStatus(jobId, isActive);
    }
}

//Use Path variables when the getting specific stuff while request Param is for optional filtering

//I need to really understand the difference between both.