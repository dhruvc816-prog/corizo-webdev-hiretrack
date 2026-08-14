package com.hiretrack.hiretrack.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiretrack.hiretrack.service.JobApplicationService;

@RestController
@RequestMapping("api/dashboard")
public class DashboardApiController {

    private final JobApplicationService service;

    public DashboardApiController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        return service.getStatusCounts();
    }
}
