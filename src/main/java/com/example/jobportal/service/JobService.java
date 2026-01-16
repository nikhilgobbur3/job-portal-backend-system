package com.example.jobportal.service;

import com.example.jobportal.dto.JobDTO;
import org.springframework.data.domain.Page;

public interface JobService {
    JobDTO createJob(JobDTO jobDTO);
    JobDTO updateJob(Long id, JobDTO jobDTO);
    void deleteJob(Long id);
    JobDTO getJob(Long id);
    Page<JobDTO> searchJobs(String title, String location, String company, String jobType, int page, int size, String sortBy);
}
