package com.example.jobportal.service.impl;

import com.example.jobportal.dto.ApplicationDTO;
import com.example.jobportal.entity.Application;
import com.example.jobportal.entity.ApplicationStatus;
import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.ApplicationRepository;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.service.ApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApplicationDTO apply(Long jobId, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        Application app = new Application();
        app.setUser(user);
        app.setJob(job);
        Application saved = applicationRepository.save(app);
        return convert(saved);
    }

    @Override
    public List<ApplicationDTO> getUserApplications(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        return applicationRepository.findByUser(user).stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO updateStatus(Long applicationId, String status) {
        Application app = applicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application not found"));
        app.setStatus(ApplicationStatus.valueOf(status.toUpperCase()));
        Application saved = applicationRepository.save(app);
        return convert(saved);
    }

    private ApplicationDTO convert(Application a) {
        ApplicationDTO dto = modelMapper.map(a, ApplicationDTO.class);
        dto.setUserId(a.getUser() != null ? a.getUser().getId() : null);
        dto.setJobId(a.getJob() != null ? a.getJob().getId() : null);
        return dto;
    }
}
