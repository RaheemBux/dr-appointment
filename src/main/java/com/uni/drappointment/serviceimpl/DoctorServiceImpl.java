package com.uni.drappointment.serviceimpl;

import com.uni.drappointment.entity.DoctorEntity;
import com.uni.drappointment.repository.DoctorRepository;
import com.uni.drappointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<DoctorEntity> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public DoctorEntity create(DoctorEntity doctorEntity) {
        return doctorRepository.save(doctorEntity);
    }

    @Override
    public DoctorEntity delete(DoctorEntity doctorEntity) {
        if (doctorEntity.getId() != null) {
            doctorEntity.setStatus(false);
            doctorRepository.save(doctorEntity);
            return doctorEntity;
        }
        return null;
    }

    @Override
    public DoctorEntity update(DoctorEntity doctorEntity) {
        if (doctorEntity.getId() != null) {
            DoctorEntity persisted = findById(doctorEntity.getId());
            if (persisted == null) {
                return null;
            }
           return doctorRepository.save(doctorEntity);
        }
        return null;
    }

    @Override
    public DoctorEntity findById(Long id) {
        Optional<DoctorEntity> optional = doctorRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Page<DoctorEntity> findAllByFilterWithPaging(Specification<DoctorEntity> specification, Pageable pageable) {
        return doctorRepository.findAll(specification, pageable);
    }


}
