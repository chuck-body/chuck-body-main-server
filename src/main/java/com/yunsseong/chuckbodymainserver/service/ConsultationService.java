package com.yunsseong.chuckbodymainserver.service;

import com.yunsseong.chuckbodymainserver.common.exception.BusinessException;
import com.yunsseong.chuckbodymainserver.common.exception.CommonStatusCode;
import com.yunsseong.chuckbodymainserver.domain.Consultation;
import com.yunsseong.chuckbodymainserver.event.ConsultationCreatedEvent;
import com.yunsseong.chuckbodymainserver.domain.Doctor;
import com.yunsseong.chuckbodymainserver.domain.Patient;
import com.yunsseong.chuckbodymainserver.repository.ConsultationRepository;
import com.yunsseong.chuckbodymainserver.repository.DoctorRepository;
import com.yunsseong.chuckbodymainserver.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Consultation createConsultation(Long doctorId, Long patientId, Consultation consultation) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new BusinessException(CommonStatusCode.NOT_FOUND));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new BusinessException(CommonStatusCode.NOT_FOUND));

        consultation.setConsultationDateTime(LocalDateTime.now());
        consultation.setDoctor(doctor);
        consultation.setPatient(patient);

        Consultation savedConsultation = consultationRepository.save(consultation);

        eventPublisher.publishEvent(new ConsultationCreatedEvent(
                savedConsultation.getId(),
                savedConsultation.getVoice()
        ));
        return savedConsultation;
    }

    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    public Consultation getConsultationById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new BusinessException(CommonStatusCode.NOT_FOUND));
    }

    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }
}