package com.example.jobportal.dto;

import com.example.jobportal.entity.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationDTO {
    private Long id;
    private LocalDateTime appliedDate;
    private ApplicationStatus status;
    private Long userId;
    private Long jobId;
}
