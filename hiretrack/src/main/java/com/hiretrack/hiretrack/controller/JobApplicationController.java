package com.hiretrack.hiretrack.controller;

import com.hiretrack.hiretrack.entity.JobApplication;
import com.hiretrack.hiretrack.enums.ApplicationStatus;
import com.hiretrack.hiretrack.service.JobApplicationService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping("/applications")
    public List<JobApplication> getAllApplications() {
        return service.getAllApplications();
    }

    @GetMapping("/applications/{id}")
    public JobApplication getApplicationById(@PathVariable Long id) {
        return service.getApplicationById(id);
    }

    @PostMapping("/createApplications")
    public JobApplication createApplication(@Valid @RequestBody JobApplication application) {
        return service.saveApplication(application);
    }

    @PutMapping("/updateApplications/{id}")
    public JobApplication updateApplication(@Valid @PathVariable Long id, @RequestBody JobApplication application) {
        return service.updateApplication(id, application);
    }

    @DeleteMapping("/applications/{id}")
    public void deleteApplication(@PathVariable Long id) {
        service.deleteApplication(id);
    }

    @GetMapping("/status/{status}")
    public List<JobApplication> getByStatus(@PathVariable ApplicationStatus status) {
        return service.getApplicationByStatus(status);
    }

    @GetMapping("/dashboard/total")
    public long getTotal() {
        return service.getTotalApplications();
    }

    @GetMapping("/dashboard/stats")
    public Map<String, Long> getStats() {
        return service.getStatusCounts();
    }
}
