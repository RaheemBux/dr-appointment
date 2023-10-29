package com.uni.drappointment.repository;

import com.uni.drappointment.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long>, JpaSpecificationExecutor<HospitalEntity> {
}
