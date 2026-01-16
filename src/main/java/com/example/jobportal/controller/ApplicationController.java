package com.example.jobportal.controller;

import com.example.jobportal.dto.ApiResponse;
import com.example.jobportal.dto.ApplicationDTO;
import com.example.jobportal.service.ApplicationService;
import com.example.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/api/user/apply/{jobId}")
    public ResponseEntity<ApiResponse<ApplicationDTO>> apply(@PathVariable Long jobId, Authentication authentication) {
        String email = authentication.getName();
        ApplicationDTO dto = applicationService.apply(jobId, email);
        return ResponseEntity.ok(new ApiResponse<>("Applied", dto, 200, null));
    }

    @GetMapping("/api/user/applications")
    public ResponseEntity<ApiResponse<List<ApplicationDTO>>> myApplications(Authentication authentication) {
        String email = authentication.getName();
        List<ApplicationDTO> list = applicationService.getUserApplications(email);
        return ResponseEntity.ok(new ApiResponse<>("Success", list, 200, null));
    }

    @GetMapping("/api/admin/applications")
    public ResponseEntity<ApiResponse<List<ApplicationDTO>>> allApplications() {
        List<ApplicationDTO> list = applicationService.getAllApplications();
        return ResponseEntity.ok(new ApiResponse<>("Success", list, 200, null));
    }

    @PutMapping("/api/admin/applications/{id}/status")
    public ResponseEntity<ApiResponse<ApplicationDTO>> updateStatus(@PathVariable Long id, @RequestParam String status) {
        ApplicationDTO dto = applicationService.updateStatus(id, status);
        return ResponseEntity.ok(new ApiResponse<>("Status updated", dto, 200, null));
    }
}
