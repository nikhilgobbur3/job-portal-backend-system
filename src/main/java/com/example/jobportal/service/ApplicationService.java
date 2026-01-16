package com.example.jobportal.service;

import com.example.jobportal.dto.ApplicationDTO;

import java.util.List;

public interface ApplicationService {
    ApplicationDTO apply(Long jobId, String userEmail);
    List<ApplicationDTO> getUserApplications(String userEmail);
    List<ApplicationDTO> getAllApplications();
    ApplicationDTO updateStatus(Long applicationId, String status);
}
