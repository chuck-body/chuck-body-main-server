package com.yunsseong.chuckbodymainserver.event;

public record ConsultationCreatedEvent(Long consultationId, Long speakerNumber, byte[] voiceFile) {
}
