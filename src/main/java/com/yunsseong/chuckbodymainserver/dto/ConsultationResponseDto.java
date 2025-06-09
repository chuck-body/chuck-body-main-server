package com.yunsseong.chuckbodymainserver.dto;

public record ConsultationResponseDto(
    Long id,
    Long patientId,
    Long doctorId,
    Long speakerNumber,
    String summary,
    String tags,
    String conversation,
    String consultationDateTime
) {
}
