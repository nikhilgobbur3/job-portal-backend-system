package com.example.jobportal.controller;

import com.example.jobportal.dto.ApiResponse;
import com.example.jobportal.dto.JobDTO;
import com.example.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/jobs")
@Validated
public class AdminJobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<ApiResponse<JobDTO>> create(@Valid @RequestBody JobDTO jobDTO) {
        JobDTO created = jobService.createJob(jobDTO);
        return ResponseEntity.status(201).body(new ApiResponse<>("Job created", created, 201, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<JobDTO>> update(@PathVariable Long id, @Valid @RequestBody JobDTO jobDTO) {
        JobDTO updated = jobService.updateJob(id, jobDTO);
        return ResponseEntity.ok(new ApiResponse<>("Job updated", updated, 200, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok(new ApiResponse<>("Job deleted", null, 200, null));
    }
}
