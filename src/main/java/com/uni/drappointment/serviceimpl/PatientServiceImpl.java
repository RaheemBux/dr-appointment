package com.uni.drappointment.serviceimpl;

import com.uni.drappointment.entity.PatientEntity;
import com.uni.drappointment.repository.PatientRepository;
import com.uni.drappointment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PatientEntity> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public PatientEntity create(PatientEntity patientEntity) {
        return patientRepository.save(patientEntity);
    }

    @Override
    public PatientEntity delete(PatientEntity patientEntity) {
        if (patientEntity.getId() != null) {
            patientEntity.setStatus(false);
            patientRepository.save(patientEntity);
            return patientEntity;
        }
        return null;
    }

    @Override
    public PatientEntity update(PatientEntity patientEntity) {
        if (patientEntity.getId() != null) {
            PatientEntity persisted = findById(patientEntity.getId());
            if (persisted == null) {
                return null;
            }
            return patientRepository.save(patientEntity);
        }
        return null;
    }

    @Override
    public PatientEntity findById(Long id) {
        Optional<PatientEntity> optional = patientRepository.findById(id);
        return optional.orElse(null);
    }

}
