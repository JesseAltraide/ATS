package com.example.ATS.Service;

import com.example.ATS.DTO.JobDTO;
import com.example.ATS.Model.Job;
import com.example.ATS.Model.Users;
import com.example.ATS.Repository.JobRepo;
import com.example.ATS.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    JobRepo jobRepo;

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<List<Job>> viewAllJobs() {

        //Find only active jobs
        //Think you have to use query

        return new ResponseEntity<>(jobRepo.findByIsActiveTrue(),  HttpStatus.OK);

    }

    public ResponseEntity<String> createJob(JobDTO job, Authentication auth) {

        Job job1 = new Job();

        job1.setJobTitle(job.getJobTitle());
        job1.setJobDescription(job.getJobDescription());
        job1.setJobType(job.getJobType());

        job1.setActive(true);
        job1.setCreatedAt(new Date(System.currentTimeMillis()));

        String username =  auth.getName();
        Users user = userRepo.findByUsername(username);

        job1.setRecruiter(user);

        jobRepo.save(job1);

        return new ResponseEntity<>("Job created", HttpStatus.OK);

    }

    public ResponseEntity<List<Job>> viewAllJobsPosted(Authentication auth) {

        String username =  auth.getName();
        Integer userId = userRepo.findByUsername(username).getId();

        return new ResponseEntity<>(jobRepo.findByRecruiterId(userId),  HttpStatus.OK);

    }

    public ResponseEntity<List<Job>> getFilteredJobs(String jobTitle) {

        List<Job> jobs = new ArrayList<>();
        jobs = jobRepo.findByJobTitle(jobTitle);

        if (jobs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(jobs, HttpStatus.OK);

    }

    public ResponseEntity<Optional<Job>> getJobById(Integer id) {
        return new ResponseEntity<>(jobRepo.findById(id),  HttpStatus.OK);
    }

    public ResponseEntity<String> deleteJobById(Integer id) {
        Optional<Job> job = jobRepo.findById(id);
        if (job.isPresent()) {
            jobRepo.delete(job.get());
            return new ResponseEntity<>("Job deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }
}
