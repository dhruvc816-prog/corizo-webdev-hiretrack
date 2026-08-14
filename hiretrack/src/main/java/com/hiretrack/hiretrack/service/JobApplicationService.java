package com.hiretrack.hiretrack.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hiretrack.hiretrack.entity.JobApplication;
import com.hiretrack.hiretrack.enums.ApplicationStatus;
import com.hiretrack.hiretrack.repository.JobApplicationRepository;
import com.hiretrack.hiretrack.exception.ResourceNotFoundException;

@Service
public class JobApplicationService {
    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    // Return all job applications in the form of list
    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }

    // Search application by id
    public JobApplication getApplicationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id doesn't exist: " + id));
    }

    // Save Job Application
    public JobApplication saveApplication(JobApplication app) {
        return repository.save(app);
    }

    // Update The application
    public JobApplication updateApplication(Long id, JobApplication updatedApp) {
        JobApplication existing = getApplicationById(id);
        existing.setCompanyName(updatedApp.getCompanyName());
        existing.setDomain(updatedApp.getDomain());
        existing.setRole(updatedApp.getRole());
        existing.setAppliedDate(updatedApp.getAppliedDate());
        existing.setStatus(updatedApp.getStatus());
        existing.setNotes(updatedApp.getNotes());
        return repository.save(existing);

    }

    // Delete Application
    public void deleteApplication(Long id) {
        repository.deleteById(id);
    }

    public List<JobApplication> getApplicationByStatus(ApplicationStatus status) {
        return repository.findByStatus(status);
    }

    // Total Applications list return
    public long getTotalApplications() {
        return repository.count();
    }

    // count of each status
    public Map<String, Long> getStatusCounts() {
        Map<String, Long> counts = new HashMap<>();
        for (ApplicationStatus status : ApplicationStatus.values()) {
            counts.put(status.name(), (long) repository.findByStatus(status).size());
        }
        return counts;
    }

}
