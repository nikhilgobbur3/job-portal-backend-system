package com.example.jobportal.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CompanyDTO {
    private Long id;

    @NotBlank
    private String name;

    private String location;
    private String website;
    private String description;
}
