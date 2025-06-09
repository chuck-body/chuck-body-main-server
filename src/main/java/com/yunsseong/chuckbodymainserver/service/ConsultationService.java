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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public Consultation createConsultation(Long doctorId, Long patientId, Long speakerNumber, MultipartFile file) throws IOException {
        Consultation consultation = new Consultation();
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new BusinessException(CommonStatusCode.NOT_FOUND));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new BusinessException(CommonStatusCode.NOT_FOUND));

        consultation.setVoice(file.getBytes());
        consultation.setSpeakerNumber(speakerNumber);
        consultation.setConsultationDateTime(LocalDateTime.now());
        consultation.setDoctor(doctor);
        consultation.setPatient(patient);

        Consultation savedConsultation = consultationRepository.save(consultation);

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        eventPublisher.publishEvent(new ConsultationCreatedEvent(
                savedConsultation.getId(),
                savedConsultation.getSpeakerNumber(),
                savedConsultation.getVoice(),
                extension
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