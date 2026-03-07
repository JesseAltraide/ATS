package com.example.ATS.Repository;

import com.example.ATS.Model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer> {

    List<Application> findByJob_Id(Integer id);

    Application findByCandidate_IdAndJob_Id(Integer userId, Integer jobId);

    List<Application> findByCandidate_Id(Integer id);

}
