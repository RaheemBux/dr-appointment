package com.uni.drappointment.service;

import com.uni.drappointment.entity.AppointmentEntity;

import java.util.List;

public interface AppointmentService {
    List<AppointmentEntity> findAll();
    AppointmentEntity create(AppointmentEntity appointmentEntity) ;
    AppointmentEntity delete(AppointmentEntity appointmentEntity);
    AppointmentEntity update(AppointmentEntity appointmentEntity);
    AppointmentEntity findById(Long id);
}
