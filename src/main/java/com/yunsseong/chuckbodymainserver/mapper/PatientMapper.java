package com.yunsseong.chuckbodymainserver.mapper;

import com.yunsseong.chuckbodymainserver.domain.Patient;
import com.yunsseong.chuckbodymainserver.dto.PatientResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {

    public PatientResponseDto toDto(Patient patient) {
        List<PatientResponseDto.ConsultationSimpleDto> consultations = patient.getConsultation().stream()
                .map(c -> new PatientResponseDto.ConsultationSimpleDto(
                        c.getId(),
                        c.getSummary(),
                        c.getTags(),
                        c.getConversation(),
                        c.getConsultationDateTime().toString()
                ))
                .collect(Collectors.toList());

        return new PatientResponseDto(
                patient.getId(),
                patient.getName(),
                patient.getBirthDate(),
                consultations
        );
    }
}