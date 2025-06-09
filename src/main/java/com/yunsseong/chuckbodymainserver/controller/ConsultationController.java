package com.yunsseong.chuckbodymainserver.controller;

import com.yunsseong.chuckbodymainserver.common.dto.ApiResponse;
import com.yunsseong.chuckbodymainserver.common.dto.ApiResponseFactory;
import com.yunsseong.chuckbodymainserver.domain.Consultation;
import com.yunsseong.chuckbodymainserver.dto.ConsultationResponseDto;
import com.yunsseong.chuckbodymainserver.mapper.ConsultationMapper;
import com.yunsseong.chuckbodymainserver.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/consultations")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;
    private final ConsultationMapper consultationMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ConsultationResponseDto>> createConsultation(
            @RequestParam Long doctorId,
            @RequestParam Long patientId,
            @RequestParam Long speakerNumber,
            @RequestParam MultipartFile voiceFile
    ) throws IOException {
        Consultation consultation = new Consultation();
        consultation.setVoice(voiceFile.getBytes());
        Consultation savedConsultation = consultationService.createConsultation(doctorId, patientId, speakerNumber, consultation);
        return ApiResponseFactory.success(consultationMapper.toDto(savedConsultation));
    }

    @GetMapping
    public ResponseEntity<List<ConsultationResponseDto>> getAllConsultations() {
        List<Consultation> consultations = consultationService.getAllConsultations();
        List<ConsultationResponseDto> result = consultations.stream()
                .map(consultationMapper::toDto)
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultationResponseDto>> getConsultationById(@PathVariable Long id) {
        return ApiResponseFactory.success(consultationMapper.toDto(consultationService.getConsultationById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ApiResponseFactory.success();
    }
}