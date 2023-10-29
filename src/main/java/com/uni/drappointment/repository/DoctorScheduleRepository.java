package com.uni.drappointment.repository;

import com.uni.drappointment.entity.DoctorScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DoctorScheduleRepository extends JpaRepository<DoctorScheduleEntity, Long>, JpaSpecificationExecutor<DoctorScheduleEntity> {
}
