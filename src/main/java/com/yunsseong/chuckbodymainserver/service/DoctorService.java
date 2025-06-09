package com.yunsseong.chuckbodymainserver.service;

import com.yunsseong.chuckbodymainserver.common.exception.BusinessException;
import com.yunsseong.chuckbodymainserver.common.exception.CommonStatusCode;
import com.yunsseong.chuckbodymainserver.domain.Doctor;
import com.yunsseong.chuckbodymainserver.dto.DoctorCreateRequest;
import com.yunsseong.chuckbodymainserver.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Doctor createDoctor(DoctorCreateRequest request) {
        Doctor doctor = new Doctor();
        doctor.setName(request.name());
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new BusinessException(CommonStatusCode.NOT_FOUND));
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}