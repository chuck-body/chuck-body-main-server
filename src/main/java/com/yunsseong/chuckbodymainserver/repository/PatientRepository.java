package com.yunsseong.chuckbodymainserver.repository;

import com.yunsseong.chuckbodymainserver.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
