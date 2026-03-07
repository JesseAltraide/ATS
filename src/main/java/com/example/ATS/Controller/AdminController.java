package com.example.ATS.Controller;


import com.example.ATS.Enum.Roles;
import com.example.ATS.Model.Job;
import com.example.ATS.Model.Users;
import com.example.ATS.Service.JobService;
import com.example.ATS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    JobService jobService;

    @Autowired
    UserService userService;

    @GetMapping("/jobs/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        return jobService.viewAllJobs();
    }

    @GetMapping("jobs/{job_id}")
    public ResponseEntity<Optional<Job>> getJobById(@PathVariable Integer jobId) {
        return jobService.getJobById(jobId);
    }

    @DeleteMapping("/job/{job_id}/delete")
    public ResponseEntity<String> deleteJobById(@PathVariable Integer jobId) {
        return jobService.deleteJobById(jobId);
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/all/{user_id}")
    public ResponseEntity<Users> viewUser (@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/user/all/{user_id}/role")
    public ResponseEntity<Users> updateUserRole(@PathVariable Integer userId, @RequestBody Roles role) {
        return userService.updateRole(userId, role);
    }

}