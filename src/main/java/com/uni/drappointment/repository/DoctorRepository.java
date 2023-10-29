package com.uni.drappointment.repository;

import com.uni.drappointment.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long>, JpaSpecificationExecutor<DoctorEntity> {
}
