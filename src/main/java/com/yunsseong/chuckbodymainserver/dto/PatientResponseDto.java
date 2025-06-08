package com.yunsseong.chuckbodymainserver.dto;

import java.time.LocalDate;
import java.util.List;

public record PatientResponseDto(
    Long id,
    String name,
    LocalDate birthDate,
    List<ConsultationSimpleDto> consultations
) {
    public record ConsultationSimpleDto(Long id, String summary, String tags, String conversation, String consultationDateTime) {}
}