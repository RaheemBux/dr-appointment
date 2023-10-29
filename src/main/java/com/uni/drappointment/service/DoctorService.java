package com.uni.drappointment.service;

import com.uni.drappointment.entity.DoctorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface DoctorService {
    List<DoctorEntity> findAll();
    DoctorEntity create(DoctorEntity doctorEntity) ;
    DoctorEntity delete(DoctorEntity doctorEntity);
    DoctorEntity update(DoctorEntity doctorEntity);
    DoctorEntity findById(Long id);
    Page<DoctorEntity> findAllByFilterWithPaging(Specification<DoctorEntity> specification, Pageable pageable);
}
