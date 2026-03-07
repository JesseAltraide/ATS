package com.example.ATS.Repository;

import com.example.ATS.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {

    List<Job> findByRecruiterId(int id);

    List<Job> findById(int id);

    List<Job> findByJobTitle(String jobTitle);

    List<Job> findByIsActiveTrue();
    List<Job> findByIsActiveFalse();

}
