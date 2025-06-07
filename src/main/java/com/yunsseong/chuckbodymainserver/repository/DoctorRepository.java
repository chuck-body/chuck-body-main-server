package com.yunsseong.chuckbodymainserver.repository;

import com.yunsseong.chuckbodymainserver.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
