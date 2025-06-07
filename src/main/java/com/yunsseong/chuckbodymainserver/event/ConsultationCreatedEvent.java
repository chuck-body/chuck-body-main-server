package com.yunsseong.chuckbodymainserver.event;

public record ConsultationCreatedEvent(Long consultationId, byte[] voiceFile) {
}
