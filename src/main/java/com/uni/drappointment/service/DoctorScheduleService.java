package com.uni.drappointment.service;

import com.uni.drappointment.entity.DoctorScheduleEntity;

import java.util.List;

public interface DoctorScheduleService {
    List<DoctorScheduleEntity> findAll();
    DoctorScheduleEntity create(DoctorScheduleEntity doctorScheduleEntity) ;
    DoctorScheduleEntity delete(DoctorScheduleEntity doctorScheduleEntity);
    DoctorScheduleEntity update(DoctorScheduleEntity doctorScheduleEntity);
    DoctorScheduleEntity findById(Long id);
}
