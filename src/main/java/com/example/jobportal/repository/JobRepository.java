package com.example.jobportal.repository;

import com.example.jobportal.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j WHERE " +
            "(:title IS NULL OR LOWER(j.title) LIKE %:title%) AND " +
            "(:location IS NULL OR LOWER(j.location) LIKE %:location%) AND " +
            "(:companyName IS NULL OR LOWER(j.company.name) LIKE %:companyName%) AND " +
            "(:jobType IS NULL OR LOWER(j.jobType) = LOWER(:jobType))")
    Page<Job> searchJobs(@Param("title") String title,
                         @Param("location") String location,
                         @Param("companyName") String companyName,
                         @Param("jobType") String jobType,
                         Pageable pageable);
}
