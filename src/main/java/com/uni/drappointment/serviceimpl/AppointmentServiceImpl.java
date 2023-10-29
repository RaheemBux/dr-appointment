package com.uni.drappointment.serviceimpl;

import com.uni.drappointment.entity.AppointmentEntity;
import com.uni.drappointment.repository.AppointmentRepository;
import com.uni.drappointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentEntity> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public AppointmentEntity create(AppointmentEntity appointmentEntity) {
        return appointmentRepository.save(appointmentEntity);
    }

    @Override
    public AppointmentEntity delete(AppointmentEntity appointmentEntity) {
        if (appointmentEntity.getId() != null) {
            appointmentEntity.setStatus(false);
            appointmentRepository.save(appointmentEntity);
            return appointmentEntity;
        }
        return null;
    }

    @Override
    public AppointmentEntity update(AppointmentEntity appointmentEntity) {
        if (appointmentEntity.getId() != null) {
            AppointmentEntity persisted = findById(appointmentEntity.getId());
            if (persisted == null) {
                return null;
            }
            return appointmentRepository.save(appointmentEntity);
        }
        return null;
    }

    @Override
    public AppointmentEntity findById(Long id) {
        Optional<AppointmentEntity> optional = appointmentRepository.findById(id);
        return optional.orElse(null);
    }
}
