package com.uni.drappointment.serviceimpl;

import com.uni.drappointment.entity.DoctorScheduleEntity;
import com.uni.drappointment.repository.DoctorScheduleRepository;
import com.uni.drappointment.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    @Autowired
    private DoctorScheduleRepository doctorScheduleRepository;

    @Override
    public List<DoctorScheduleEntity> findAll() {
        return doctorScheduleRepository.findAll();
    }

    @Override
    public DoctorScheduleEntity create(DoctorScheduleEntity doctorScheduleEntity) {
        return doctorScheduleRepository.save(doctorScheduleEntity);
    }

    @Override
    public DoctorScheduleEntity delete(DoctorScheduleEntity doctorScheduleEntity) {
        if (doctorScheduleEntity.getId() != null) {
            doctorScheduleEntity.setStatus(false);
            doctorScheduleRepository.save(doctorScheduleEntity);
            return doctorScheduleEntity;
        }
        return null;
    }

    @Override
    public DoctorScheduleEntity update(DoctorScheduleEntity doctorScheduleEntity) {
        if (doctorScheduleEntity.getId() != null) {
            DoctorScheduleEntity persisted = findById(doctorScheduleEntity.getId());
            if (persisted == null) {
                return null;
            }
            return doctorScheduleRepository.save(doctorScheduleEntity);
        }
        return null;
    }

    @Override
    public DoctorScheduleEntity findById(Long id) {
        Optional<DoctorScheduleEntity> optional = doctorScheduleRepository.findById(id);
        return optional.orElse(null);
    }
}
