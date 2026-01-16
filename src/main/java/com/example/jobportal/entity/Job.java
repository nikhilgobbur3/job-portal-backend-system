package com.example.jobportal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 4000)
    private String description;

    private String location;
    private String salary;
    private String jobType; // Full-time, Internship, etc.

    private LocalDate postedDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
