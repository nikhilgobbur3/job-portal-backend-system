package com.example.jobportal.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class JobDTO {
    private Long id;

    @NotBlank
    private String title;

    private String description;
    private String location;
    private String salary;
    private String jobType;
    private LocalDate postedDate;
    private Long companyId;
    private String companyName;
}
