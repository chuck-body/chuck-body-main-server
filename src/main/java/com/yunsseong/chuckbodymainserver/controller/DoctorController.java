package com.yunsseong.chuckbodymainserver.controller;

import com.yunsseong.chuckbodymainserver.common.dto.ApiResponse;
import com.yunsseong.chuckbodymainserver.common.dto.ApiResponseFactory;
import com.yunsseong.chuckbodymainserver.domain.Doctor;
import com.yunsseong.chuckbodymainserver.dto.DoctorResponseDto;
import com.yunsseong.chuckbodymainserver.mapper.DoctorMapper;
import com.yunsseong.chuckbodymainserver.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorResponseDto>> createDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.createDoctor(doctor);
        return ApiResponseFactory.success(doctorMapper.toDto(savedDoctor));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DoctorResponseDto>>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<DoctorResponseDto> result = doctors.stream()
                .map(doctorMapper::toDto)
                .toList();

        return ApiResponseFactory.success(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> getDoctorById(@PathVariable Long id) {
        return ApiResponseFactory.success(doctorMapper.toDto(doctorService.getDoctorById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ApiResponseFactory.success();
    }
}