package com.uni.drappointment.serviceimpl;

import com.uni.drappointment.entity.HospitalEntity;
import com.uni.drappointment.repository.HospitalRepository;
import com.uni.drappointment.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<HospitalEntity> findAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public HospitalEntity create(HospitalEntity hospitalEntity) {
        return hospitalRepository.save(hospitalEntity);
    }

    @Override
    public HospitalEntity delete(HospitalEntity hospitalEntity) {
        if (hospitalEntity.getId() != null) {
            hospitalEntity.setStatus(false);
            hospitalRepository.save(hospitalEntity);
            return hospitalEntity;
        }
        return null;
    }

    @Override
    public HospitalEntity update(HospitalEntity hospitalEntity) {
        if (hospitalEntity.getId() != null) {
            HospitalEntity persisted = findById(hospitalEntity.getId());
            if (persisted == null) {
                return null;
            }
            return hospitalRepository.save(hospitalEntity);
        }
        return null;
    }

    @Override
    public HospitalEntity findById(Long id) {
        Optional<HospitalEntity> optional = hospitalRepository.findById(id);
        return optional.orElse(null);
    }
}
