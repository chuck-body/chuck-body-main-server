package com.yunsseong.chuckbodymainserver.dto;

public record ConsultationResponseDto(
    Long id,
    Long patientId,
    Long doctorId,
    String summary,
    String tags,
    String conversation
) {
}
