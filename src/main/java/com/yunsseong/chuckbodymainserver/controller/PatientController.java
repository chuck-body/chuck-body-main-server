package com.yunsseong.chuckbodymainserver.controller;

import com.yunsseong.chuckbodymainserver.common.dto.ApiResponse;
import com.yunsseong.chuckbodymainserver.common.dto.ApiResponseFactory;
import com.yunsseong.chuckbodymainserver.domain.Patient;
import com.yunsseong.chuckbodymainserver.dto.PatientCreateRequest;
import com.yunsseong.chuckbodymainserver.dto.PatientResponseDto;
import com.yunsseong.chuckbodymainserver.mapper.PatientMapper;
import com.yunsseong.chuckbodymainserver.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<PatientResponseDto>> createPatient(@RequestBody PatientCreateRequest request) {
        Patient savedPatient = patientService.createPatient(request);
        return ApiResponseFactory.success(patientMapper.toDto(savedPatient));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PatientResponseDto>>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        List<PatientResponseDto> result = patients.stream()
                .map(patientMapper::toDto)
                .toList();
        return ApiResponseFactory.success(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> getPatientById(@PathVariable Long id) {
        return ApiResponseFactory.success(patientMapper.toDto(patientService.getPatientById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ApiResponseFactory.success();
    }
}