package com.example.jobportal.controller;

import com.example.jobportal.dto.ApiResponse;
import com.example.jobportal.dto.CompanyDTO;
import com.example.jobportal.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
@Validated
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyDTO>> create(@Valid @RequestBody CompanyDTO dto) {
        CompanyDTO created = companyService.create(dto);
        return ResponseEntity.status(201).body(new ApiResponse<>("Company created", created, 201, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyDTO>> update(@PathVariable Long id, @Valid @RequestBody CompanyDTO dto) {
        CompanyDTO updated = companyService.update(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("Company updated", updated, 200, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>("Company deleted", null, 200, null));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<CompanyDTO>>> list() {
        List<CompanyDTO> list = companyService.list();
        return ResponseEntity.ok(new ApiResponse<>("Success", list, 200, null));
    }
}
