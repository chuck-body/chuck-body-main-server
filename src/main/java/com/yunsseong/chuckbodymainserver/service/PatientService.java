package com.yunsseong.chuckbodymainserver.service;

import com.yunsseong.chuckbodymainserver.common.exception.BusinessException;
import com.yunsseong.chuckbodymainserver.common.exception.CommonStatusCode;
import com.yunsseong.chuckbodymainserver.domain.Patient;
import com.yunsseong.chuckbodymainserver.dto.PatientCreateRequest;
import com.yunsseong.chuckbodymainserver.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient createPatient(PatientCreateRequest request) {
        Patient patient = Patient.builder()
                .name(request.name())
                .birthDate(request.birthDate())
                .build();
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new BusinessException(CommonStatusCode.NOT_FOUND));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}