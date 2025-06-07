package com.yunsseong.chuckbodymainserver.repository;

import com.yunsseong.chuckbodymainserver.domain.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
