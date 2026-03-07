package com.example.ATS.Controller;

import com.example.ATS.DTO.ApplicationDTO;
import com.example.ATS.Model.Application;
import com.example.ATS.Model.Job;
import com.example.ATS.Service.ApplicationService;
import com.example.ATS.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    JobService jobService;

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/allJobs")
    public ResponseEntity<List<Job>> viewAllJobs(Authentication authentication) {
        return jobService.viewAllJobs();
    }

    @GetMapping("/allApplications")
    public ResponseEntity<List<Application>> viewAllApplications(Authentication authentication) {
        return applicationService.viewAllApplications(authentication);
    }

    @PostMapping("/apply")
    public ResponseEntity<String> applyForJob(@RequestBody ApplicationDTO applicationDTO, Authentication authentication) {
        return applicationService.applyforJob(applicationDTO, authentication);
    }

    @GetMapping("/findjob")
    public ResponseEntity<List<Job>> getFilteredJobs(@RequestParam String jobTitle, Authentication authentication) {
        return jobService.getFilteredJobs(jobTitle);
    }


}
