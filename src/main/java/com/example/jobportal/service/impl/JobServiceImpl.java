package com.example.jobportal.service.impl;

import com.example.jobportal.dto.JobDTO;
import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public JobDTO createJob(JobDTO jobDTO) {
        Job job = modelMapper.map(jobDTO, Job.class);
        if (jobDTO.getCompanyId() != null) {
            Company company = companyRepository.findById(jobDTO.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
            job.setCompany(company);
        }
        Job saved = jobRepository.save(job);
        JobDTO out = modelMapper.map(saved, JobDTO.class);
        out.setCompanyId(saved.getCompany() != null ? saved.getCompany().getId() : null);
        out.setCompanyName(saved.getCompany() != null ? saved.getCompany().getName() : null);
        return out;
    }

    @Override
    public JobDTO updateJob(Long id, JobDTO jobDTO) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setSalary(jobDTO.getSalary());
        job.setJobType(jobDTO.getJobType());
        if (jobDTO.getCompanyId() != null) {
            Company company = companyRepository.findById(jobDTO.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
            job.setCompany(company);
        }
        Job saved = jobRepository.save(job);
        JobDTO out = modelMapper.map(saved, JobDTO.class);
        out.setCompanyId(saved.getCompany() != null ? saved.getCompany().getId() : null);
        out.setCompanyName(saved.getCompany() != null ? saved.getCompany().getName() : null);
        return out;
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public JobDTO getJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        JobDTO out = modelMapper.map(job, JobDTO.class);
        out.setCompanyId(job.getCompany() != null ? job.getCompany().getId() : null);
        out.setCompanyName(job.getCompany() != null ? job.getCompany().getName() : null);
        return out;
    }

    @Override
    public Page<JobDTO> searchJobs(String title, String location, String company, String jobType, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy == null ? "postedDate" : sortBy).descending());
        Page<Job> jobs = jobRepository.searchJobs(title == null ? null : title.toLowerCase(),
                location == null ? null : location.toLowerCase(),
                company == null ? null : company.toLowerCase(),
                jobType == null ? null : jobType,
                pageable);
        List<JobDTO> dtos = jobs.stream().map(j -> {
            JobDTO d = modelMapper.map(j, JobDTO.class);
            d.setCompanyId(j.getCompany() != null ? j.getCompany().getId() : null);
            d.setCompanyName(j.getCompany() != null ? j.getCompany().getName() : null);
            return d;
        }).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, jobs.getTotalElements());
    }
}
