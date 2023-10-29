package com.uni.drappointment.service;

import com.uni.drappointment.entity.PatientEntity;

import java.util.List;

public interface PatientService {
    List<PatientEntity> findAll();
    PatientEntity create(PatientEntity patientEntity) ;
    PatientEntity delete(PatientEntity patientEntity);
    PatientEntity update(PatientEntity patientEntity);
    PatientEntity findById(Long id);
}
