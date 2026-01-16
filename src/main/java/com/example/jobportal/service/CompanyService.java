package com.example.jobportal.service;

import com.example.jobportal.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    CompanyDTO create(CompanyDTO dto);
    CompanyDTO update(Long id, CompanyDTO dto);
    void delete(Long id);
    CompanyDTO get(Long id);
    List<CompanyDTO> list();
}
