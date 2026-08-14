package com.hiretrack.hiretrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiretrack.hiretrack.entity.JobApplication;
import com.hiretrack.hiretrack.enums.ApplicationStatus;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByStatus(ApplicationStatus status);

}
