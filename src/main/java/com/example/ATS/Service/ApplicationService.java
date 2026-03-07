package com.example.ATS.Service;

import com.example.ATS.DTO.ApplicationDTO;
import com.example.ATS.DTO.ChangeStatus;
import com.example.ATS.Enum.Roles;
import com.example.ATS.Enum.Status;
import com.example.ATS.Model.Application;
import com.example.ATS.Model.Job;
import com.example.ATS.Model.Users;
import com.example.ATS.Repository.ApplicationRepo;
import com.example.ATS.Repository.JobRepo;
import com.example.ATS.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepo applicationRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    JobRepo jobRepo;

    public ResponseEntity<List<Application>> viewAllApplications(Authentication authentication)
    {

        String username = authentication.getName();

        Integer userId = userRepo.findByUsername(username).getId();

        return new ResponseEntity<>(applicationRepo.findByCandidate_Id(userId), HttpStatus.OK);

    }

    public ResponseEntity<List<Application>> getApplications(Integer jobId, Authentication authentication) {

        String username =  authentication.getName();
        Users user = userRepo.findByUsername(username);

        Optional<Job> jobOpt = jobRepo.findById(jobId);

        if(jobOpt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Job job = jobOpt.get();

        if (!job.getRecruiter().getId().equals(user.getId())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Application> applications = applicationRepo.findByJob_Id(jobId);
        return new ResponseEntity<>(applications, HttpStatus.OK);

    }


    public ResponseEntity<Application> getApplicationById(Integer candidateId, Integer jobId) {

        Application application = applicationRepo.findByCandidate_IdAndJob_Id(candidateId, jobId);
        return new ResponseEntity<>(application, HttpStatus.OK);

    }

    public ResponseEntity<String> changeStatus(Integer jobId, Integer applicationId,ChangeStatus changeStatus, Authentication authentication) {

        //for now, I can do this

        Application application = applicationRepo.findByCandidate_IdAndJob_Id(applicationId, jobId);

        if(application == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Path variable for the id we want to update. Use the {id} then pass it as a path variable to find the whatever, then you can collect it and use it to query the database


        changeStatus.setStatus(changeStatus.getStatus().toUpperCase());

        Status status = Status.valueOf(changeStatus.getStatus());

        application.setStatus(status);

        application.setLastUpdated(new Date(System.currentTimeMillis()));

        applicationRepo.save(application);

        return new ResponseEntity<>("Status Updated",HttpStatus.OK);
    }

    public ResponseEntity<String> applyforJob(ApplicationDTO applicationDTO, Authentication authentication) {

        Users user = userRepo.findByUsername(authentication.getName());

        Application check = applicationRepo.findByCandidate_IdAndJob_Id(user.getId(), applicationDTO.getJob().getJobId());

        if (check != null) {

            return new ResponseEntity<>("User has already applied", HttpStatus.EXPECTATION_FAILED);

        }

        Application application = new  Application();

        application.setResumeUrl(applicationDTO.getResumeUrl());
        application.setJob(applicationDTO.getJob());
        application.setCandidate(user);
        application.setStatus(Status.APPLIED);
        application.setAppliedAt(new Date(System.currentTimeMillis()));
        application.setLastUpdated(new Date(System.currentTimeMillis()));
        applicationRepo.save(application);

        return new ResponseEntity<>("Application Successful",HttpStatus.OK);

    }

    public ResponseEntity<String> changeJobStatus(Integer jobId, Boolean isActive) {

        Job job = jobRepo.findById(jobId).get();

        if (job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        job.setActive(isActive);

        jobRepo.save(job);
        return new ResponseEntity<>("Job Status Updated",HttpStatus.OK);

    }
}
