package com.yunsseong.chuckbodymainserver.mapper;

import com.yunsseong.chuckbodymainserver.domain.Doctor;
import com.yunsseong.chuckbodymainserver.dto.DoctorResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public DoctorResponseDto toDto(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getId(),
                doctor.getName()
        );
    }
}