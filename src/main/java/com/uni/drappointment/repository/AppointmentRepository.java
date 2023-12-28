package com.uni.drappointment.repository;

import com.uni.drappointment.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long>, JpaSpecificationExecutor<AppointmentEntity> {
    List<AppointmentEntity> findAllByPatientId(Long patientId);
    List<AppointmentEntity> findAllByDoctorId(Long doctorId);
}
