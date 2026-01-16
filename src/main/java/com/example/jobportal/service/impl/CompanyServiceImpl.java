package com.example.jobportal.service.impl;

import com.example.jobportal.dto.CompanyDTO;
import com.example.jobportal.entity.Company;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CompanyDTO create(CompanyDTO dto) {
        Company c = modelMapper.map(dto, Company.class);
        Company saved = companyRepository.save(c);
        return modelMapper.map(saved, CompanyDTO.class);
    }

    @Override
    public CompanyDTO update(Long id, CompanyDTO dto) {
        Company c = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
        c.setName(dto.getName());
        c.setLocation(dto.getLocation());
        c.setWebsite(dto.getWebsite());
        c.setDescription(dto.getDescription());
        Company saved = companyRepository.save(c);
        return modelMapper.map(saved, CompanyDTO.class);
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyDTO get(Long id) {
        Company c = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
        return modelMapper.map(c, CompanyDTO.class);
    }

    @Override
    public List<CompanyDTO> list() {
        return companyRepository.findAll().stream().map(c -> modelMapper.map(c, CompanyDTO.class)).collect(Collectors.toList());
    }
}
