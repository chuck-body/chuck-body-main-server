package com.yunsseong.chuckbodymainserver.mapper;

import com.yunsseong.chuckbodymainserver.domain.Consultation;
import com.yunsseong.chuckbodymainserver.dto.ConsultationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ConsultationMapper {

    public ConsultationResponseDto toDto(Consultation consultation) {
        return new ConsultationResponseDto(
                consultation.getId(),
                consultation.getPatient().getId(),
                consultation.getDoctor().getId(),
                consultation.getSpeakerNumber(),
                consultation.getSummary(),
                consultation.getTags(),
                consultation.getConversation(),
                consultation.getConsultationDateTime().toString()
        );
    }
}