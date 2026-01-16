package com.example.jobportal.controller;

import com.example.jobportal.dto.ApiResponse;
import com.example.jobportal.dto.JobDTO;
import com.example.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/jobs")
@Validated
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<JobDTO>>> list(@RequestParam(required = false) String title,
                                                        @RequestParam(required = false) String location,
                                                        @RequestParam(required = false) String company,
                                                        @RequestParam(required = false) String jobType,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(required = false) String sortBy) {
        Page<JobDTO> results = jobService.searchJobs(title, location, company, jobType, page, size, sortBy);
        ApiResponse<Page<JobDTO>> resp = new ApiResponse<>("Success", results, 200, null);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobDTO>> get(@PathVariable Long id) {
        JobDTO dto = jobService.getJob(id);
        return ResponseEntity.ok(new ApiResponse<>("Success", dto, 200, null));
    }
}
